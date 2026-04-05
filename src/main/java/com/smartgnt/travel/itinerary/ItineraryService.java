package com.smartgnt.travel.itinerary;

import com.smartgnt.travel.common.TravelRequest;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ItineraryService {

    private ChatClient openAiApi;
    private ChatClient grokApi;

            public ItineraryService(@Qualifier("groqChatClient") ChatClient grokApi, @Qualifier("openAiChatClient") ChatClient openAiApi) {
        this.openAiApi = openAiApi;
        this.grokApi = grokApi;
    }

    public Itinerary ask(TravelRequest request) {
        ChatClient chat = request.model().equals("openai") ? openAiApi : grokApi;
        return chat.prompt().user(u -> u.text("""
                                You are a travel expert. Generate a detailed day-by-day itinerary for a trip to {destination}\s
                                for {days} days with a total budget of {budget} euros.
                                
                                For each day, provide a list of time slots with a specific time in HH:mm format,\s
                                the activity name, and a short description.
                                
                                Return a structured response with the destination, number of days, and the list of days.""")
                        .param("destination", request.destination())
                        .param("days", request.days())
                        .param("budget", request.budget()))
                .call()
                .entity(Itinerary.class);
    }
}
