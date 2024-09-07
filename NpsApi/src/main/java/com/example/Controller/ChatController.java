package com.example.Controller;

import com.example.Model.ChatResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/database")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping("/getChatResponse")
    public String getChatResponse() {
        return chatService.getChatText();
    }
}
