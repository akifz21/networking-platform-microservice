package com.akifozdemir.messageservice.controllers;

import com.akifozdemir.messageservice.dtos.RoomRequest;
import com.akifozdemir.messageservice.models.Room;
import com.akifozdemir.messageservice.services.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;
    public RoomController(RoomService roomService){
        this.roomService = roomService;
    }

    @PostMapping("/create")
    public ResponseEntity<Room> createRoom(@RequestBody RoomRequest roomRequest){
        Room createdRoom = this.roomService.addRoom(roomRequest);
        return ResponseEntity.ok().body(createdRoom);
    }

    @GetMapping("/")
    public ResponseEntity<Room> getRoom(@RequestParam String senderId,@RequestParam String receiverId){
        return ResponseEntity.ok().body(this.roomService.getRoomByUsers(senderId,receiverId));
    }
}
