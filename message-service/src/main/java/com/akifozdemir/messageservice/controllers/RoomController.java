package com.akifozdemir.messageservice.controllers;

import com.akifozdemir.messageservice.models.Room;
import com.akifozdemir.messageservice.services.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;
    public RoomController(RoomService roomService){
        this.roomService = roomService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createRoom(Room room){
        this.roomService.addRoom(room);
        return ResponseEntity.ok().body("Room Created");
    }
}
