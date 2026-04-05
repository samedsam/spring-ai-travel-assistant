package com.smartgnt.travel.chat;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("api/travel/chat")
    public String generation(@RequestParam String input, @RequestParam String conversationId ) {
        return chatService.ask(input, conversationId );
    }
}
