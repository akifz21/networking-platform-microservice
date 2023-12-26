package com.akifozdemir.messageservice.services;

import com.akifozdemir.messageservice.dtos.RoomRequest;
import com.akifozdemir.messageservice.models.Room;
import com.akifozdemir.messageservice.repositories.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    public RoomService(RoomRepository roomRepository){
        this.roomRepository = roomRepository;
    }

    @Transactional
    public Room addRoom(RoomRequest roomRequest){
        Optional<Room> existedRoom = this.roomRepository
                .findBySenderUserIdAndReceiverUserId(roomRequest.senderUserId(),roomRequest.receiverUserId());
        Optional<Room> existedRoom2 = this.roomRepository
                .findBySenderUserIdAndReceiverUserId(roomRequest.receiverUserId(),roomRequest.senderUserId());
        if (existedRoom.isPresent()){
            return existedRoom.get();
        }
        if (existedRoom2.isPresent()){
            return existedRoom2.get();
        }
        Room room = new Room();
        room.setSenderUserId(roomRequest.senderUserId());
        room.setReceiverUserId(roomRequest.receiverUserId());
        return this.roomRepository.save(room);
    }


    public Room getRoomByUsers(String senderId,String receiverId){
        return this.roomRepository.findBySenderUserIdAndReceiverUserId(senderId,receiverId)
                .orElseThrow(()->new RuntimeException("Room not found"));
    }

}
