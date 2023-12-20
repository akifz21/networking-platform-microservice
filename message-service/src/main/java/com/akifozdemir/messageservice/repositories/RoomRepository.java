package com.akifozdemir.messageservice.repositories;

import com.akifozdemir.messageservice.models.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomRepository extends MongoRepository<Room, String> {
}
