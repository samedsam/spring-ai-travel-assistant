package com.smartgnt.travel.chat;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    ChatClient chatClientWithMemory;

    public ChatService(@Qualifier("groqChatClient") ChatClient chatClientWithMemory) {
        ChatMemory chatMemory = MessageWindowChatMemory.builder().build();
        this.chatClientWithMemory = chatClientWithMemory.mutate().defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build()).build();
    }

    public String ask(String input, String conversationId) {
        return chatClientWithMemory.prompt().user(input).advisors(a -> a.param(ChatMemory.CONVERSATION_ID, conversationId)).call().content();
    }

}
