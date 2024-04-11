package org.acme;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class ChatMemoryBean implements Supplier<ChatMemoryProvider> {

    private final Map<Object, ChatMemory> memories = new ConcurrentHashMap<>();
    //PersistentChatMemoryStore store = new PersistentChatMemoryStore();

    @PreDestroy
    public void close() {
        memories.clear();
    }

    @Override
    public ChatMemoryProvider get() {
      ChatMemoryProvider chatMemoryProvider = memoryId -> MessageWindowChatMemory.builder()
      .id(memoryId)
      .maxMessages(10)
      //.chatMemoryStore(store)
      .build();


      return chatMemoryProvider;
    }
}
