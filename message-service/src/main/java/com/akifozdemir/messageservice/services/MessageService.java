package com.akifozdemir.messageservice.services;

import com.akifozdemir.messageservice.dtos.ChatMessage;
import com.akifozdemir.messageservice.models.Message;
import com.akifozdemir.messageservice.repositories.MessageRepository;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    public void saveMessage(Message message) {
        this.messageRepository.save(message);
    }

    public List<Message> getMessagesByRoom(String roomId){
        List<Message> messages = this.messageRepository.findByRoomId(roomId);
        Collections.reverse(messages);
        return messages;
    }
}
