package com.smartgnt.travel.compare;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

@Service
public class CompareService {
    private final ChatClient groqChatClient;
    private final ChatClient openAiChatClient;

    public CompareService(@Qualifier("groqChatClient") ChatClient groqChatClient, @Qualifier("openAiChatClient") ChatClient openAiChatClient) {
        this.groqChatClient = groqChatClient;
        this.openAiChatClient = openAiChatClient;
    }

    public Map<String, String> ask(String input) throws ExecutionException, InterruptedException {
        try (var excutor = Executors.newVirtualThreadPerTaskExecutor())
        {
             var groqFuture =excutor.submit(() -> groqChatClient.prompt(input).call().content());
             var openAiFuture =excutor.submit(() -> openAiChatClient.prompt(input).call().content());
            return Map.of(
                    "groq", groqFuture.get(),
                    "openai", openAiFuture.get()
            );

        }
    }

}
