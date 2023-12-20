package com.akifozdemir.messageservice.controllers;

import com.akifozdemir.messageservice.models.Message;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SocketController {
    @MessageMapping("/message/{roomId}")
    @SendTo("/topic/messages/")
    public String processMessage(@DestinationVariable String roomId, Message message) {
        System.out.println(message.getMessage());
        return message.getMessage();
    }
}
