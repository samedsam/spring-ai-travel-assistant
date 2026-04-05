package com.smartgnt.travel.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {
    @Bean
    ChatClient groqChatClient(OpenAiChatModel baseChatModel,
                              OpenAiApi baseOpenAiApi,
                              @Value("${groq.api-key}") String apiKey,
                              @Value("${groq.base-url}") String baseUrl,
                              @Value("${groq.model}") String model) {
        OpenAiApi groqApi = baseOpenAiApi.mutate()
                .baseUrl(baseUrl)
                .apiKey(apiKey)
                .build();

        OpenAiChatModel groqModel = baseChatModel.mutate()
                .openAiApi(groqApi)
                .defaultOptions(OpenAiChatOptions.builder().model(model).build())
                .build();
        return ChatClient.create(groqModel);
    }

    @Bean
    ChatClient openAiChatClient(OpenAiChatModel chatModel){
        return ChatClient.create(chatModel);
    }

}
