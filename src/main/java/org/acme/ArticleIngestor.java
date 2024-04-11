package org.acme;

import static dev.langchain4j.data.document.splitter.DocumentSplitters.recursive;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.net.URIBuilder;

import dev.langchain4j.data.document.Metadata;
import dev.langchain4j.model.embedding.E5SmallV2QuantizedEmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import io.quarkiverse.langchain4j.redis.RedisEmbeddingStore;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

@ApplicationScoped
public class ArticleIngestor {
  /**
   * The embedding store (the database).
   * The bean is provided by the quarkus-langchain4j-redis extension.
   */
  @Inject
  RedisEmbeddingStore store;

  /**
   * The embedding model (how the vector of a document is computed).
   * The bean is provided by the LLM (like openai) extension.
   */
  @Inject
  E5SmallV2QuantizedEmbeddingModel embeddingModel;

  public void ingest(@Observes StartupEvent event)
      throws UnsupportedEncodingException, IOException, URISyntaxException {

    List<String> articleURLs=findAllArticles();    

    System.out.println(articleURLs.toString());
    System.out.println(articleURLs.size());

    List<dev.langchain4j.data.document.Document> documents = articleURLs.stream()
        .map(articleUrl -> parseArticle(articleUrl)).map(pair -> fromStringListToDocument(pair)).toList();

    var ingestor = EmbeddingStoreIngestor.builder()
        .embeddingStore(store)
        .embeddingModel(embeddingModel)
        .documentSplitter(recursive(500, 0))
        .build();
    ingestor.ingest(documents);
    System.out.printf("Ingested %d documents.%n", documents.size());
  }

  private List<String> findAllArticles() throws StreamReadException, DatabindException, URISyntaxException, IOException{
    List<String> articleURLs = new ArrayList<String>();
    Boolean more = true;
    int page = 0;
    while (more) {
      Pair<List<String>, Boolean> pair = findArticles(page);
      articleURLs.addAll(pair.getLeft());
      more = pair.getRight();
      if (more) {
        page++;
      }
    }
    return articleURLs;
  }

  // https://www.redhat.com/rhdc/jsonapi/solr_search/blog_author_list?page=0&language=en&fq=post_author_nids:"472441"
  private Pair<List<String>, Boolean> findArticles(int page)
      throws URISyntaxException, StreamReadException, DatabindException, IOException {
    ObjectMapper mapper = new ObjectMapper();
    URL url = new URIBuilder("https://www.redhat.com/rhdc/jsonapi/solr_search/blog_author_list")
        .setParameter("page", String.valueOf(page)).setParameter("language", "enl")
        .setParameter("fq", "post_author_nids:\"472441\"").build().toURL();
    BlogPostSummaryRoot blogPostSummaryRoot = mapper.readValue(url, BlogPostSummaryRoot.class);
    return Pair.of(blogPostSummaryRoot.body.docs.stream().map(item -> item.getUrl()).toList(),
        blogPostSummaryRoot.body.numFound > blogPostSummaryRoot.body.start + blogPostSummaryRoot.body.docs.size());
  }

  private Pair<List<String>,String> parseArticle(String urlString) {
    try {
      Document doc = Jsoup.connect(urlString).get();
      return Pair.of(doc.select("p").stream().map(element -> element.text().replace('\n', ' ')).toList(),urlString);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private dev.langchain4j.data.document.Document fromStringListToDocument(Pair<List<String>,String> pair) {
    return dev.langchain4j.data.document.Document.from(pair.getLeft().stream().collect(Collectors.joining(" ")),Metadata.from("url",pair.getRight()));
  }

}
