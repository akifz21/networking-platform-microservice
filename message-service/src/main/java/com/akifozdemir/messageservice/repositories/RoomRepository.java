package com.akifozdemir.messageservice.repositories;

import com.akifozdemir.messageservice.models.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoomRepository extends MongoRepository<Room, String> {
    Optional<Room> findBySenderUserIdAndReceiverUserId(String senderUserId, String ReceiverUserId);
}
