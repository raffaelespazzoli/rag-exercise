package org.acme;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.E5SmallV2QuantizedEmbeddingModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.retriever.EmbeddingStoreRetriever;
import dev.langchain4j.retriever.Retriever;
import dev.langchain4j.store.embedding.EmbeddingStore;

@ApplicationScoped
public class ArticleEmbeddingRetriever implements Retriever<TextSegment> {

    private final EmbeddingStoreRetriever retriever;

    ArticleEmbeddingRetriever(EmbeddingStore store, E5SmallV2QuantizedEmbeddingModel model) {
        retriever = EmbeddingStoreRetriever.from(store, model, 20);
    }

    @Override
    public List<TextSegment> findRelevant(String s) {
        return retriever.findRelevant(s);
    }
}