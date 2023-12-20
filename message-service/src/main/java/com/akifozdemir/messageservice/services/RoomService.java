package com.akifozdemir.messageservice.services;

import com.akifozdemir.messageservice.models.Message;
import com.akifozdemir.messageservice.models.Room;
import com.akifozdemir.messageservice.repositories.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    public RoomService(RoomRepository roomRepository){
        this.roomRepository = roomRepository;
    }

    public void addRoom(Room room){
        this.roomRepository.save(room);
    }

}
