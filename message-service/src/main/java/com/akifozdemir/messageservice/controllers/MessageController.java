package com.akifozdemir.messageservice.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {
    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public String processMessage(String message) {
        System.out.println(message);
        return message;
    }
}
