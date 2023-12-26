package com.akifozdemir.messageservice.controllers;

import com.akifozdemir.messageservice.dtos.ChatMessage;
import com.akifozdemir.messageservice.models.Message;
import com.akifozdemir.messageservice.services.MessageService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SocketController {
    private final MessageService messageService;

    public SocketController(MessageService messageService){
        this.messageService = messageService;
    }
    @MessageMapping("/message/{roomId}")
    @SendTo("/topic/messages/{roomId}")
    public ChatMessage processMessage(@DestinationVariable String roomId, @Payload  ChatMessage message) {
        Message messageToSave = new Message();
        messageToSave.setMessage(message.getContent());
        messageToSave.setRoomId(roomId);
        this.messageService.saveMessage(messageToSave);
        return message;
    }
}
