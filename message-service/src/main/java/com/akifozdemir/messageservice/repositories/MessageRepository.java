package com.akifozdemir.messageservice.repositories;

import com.akifozdemir.messageservice.models.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message, String> {
    List<Message> findByRoomId(String roomId);
}
