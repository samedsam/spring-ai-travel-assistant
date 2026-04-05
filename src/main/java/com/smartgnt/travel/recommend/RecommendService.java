package com.smartgnt.travel.recommend;

import com.smartgnt.travel.common.TravelRequest;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class RecommendService {
    private final ChatClient groqChatClient;
    private final ChatClient openAiChatClient;

    public RecommendService(@Qualifier("groqChatClient") ChatClient groqChatClient,@Qualifier("openAiChatClient") ChatClient openAiChatClient) {
        this.groqChatClient = groqChatClient;
        this.openAiChatClient = openAiChatClient;
    }

    public String ask(TravelRequest request) {
        ChatClient chat = request.model().equals("openai") ? openAiChatClient : groqChatClient;
        return chat.prompt().user(u -> u.text("""
                        Recommend things to do in {destination} for {days} days 
                        with a budget of {budget} euros.
                        """).param("destination", request.destination())
                .param("days", request.days())
                .param("budget", request.budget())).call().content();
    }
}
