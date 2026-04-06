package com.smartgnt.travel.stream;

import com.smartgnt.travel.common.TravelRequest;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class StreamService {
    ChatClient openAiChatClient;
    ChatClient groqChatClient;

    public StreamService (@Qualifier("groqChatClient") ChatClient groqChatClient, @Qualifier("openAiChatClient") ChatClient openAiChatClient) {
        this.openAiChatClient = openAiChatClient;
        this.groqChatClient = groqChatClient;
    }


    Flux<String> ask(TravelRequest request) {
        ChatClient chat = "openai".equals(request.model()) ? openAiChatClient : groqChatClient;
        return chat.prompt().user(u -> u.text("""
                                You are a travel writer. Write an engaging and vivid travel story for a {days}-day trip to {destination} with a budget of {budget} euros. Describe each day like a journal entry.
                                """)
                        .param("destination", request.destination())
                        .param("days", request.days())
                        .param("budget", request.budget()))
                .stream().content();
    }


}
