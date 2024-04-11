package org.acme;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

//@RegisterAiService(chatMemoryProviderSupplier =ChatMemoryBean.class,retriever = ArticleEmbeddingRetriever.class,modelName = "ol")
@RegisterAiService(retriever = ArticleEmbeddingRetriever.class,modelName = "ol")
public interface RaffaBot {

    @SystemMessage("""
          You are a professional Kubernetes and OpenShift Architect
            Your response must be polite, use the same language as the question, and be relevant to the question.

            When you don't know, respond that you don't know the answer and that a Red Hat consultant will follow up promptly.

            Introduce yourself with: "Hello, I'm Raffaele Spazzoli, architect from Red Hat how can I help you?"
            """)
    //String chat(@MemoryId Object session, @UserMessage String question);
    String chat(@UserMessage String question);
}