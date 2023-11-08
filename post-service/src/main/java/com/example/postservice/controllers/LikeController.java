package com.example.postservice.controllers;

import com.example.postservice.dtos.requests.LikeRequest;
import com.example.postservice.dtos.respones.LikeResponse;
import com.example.postservice.services.LikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/likes")
public class LikeController {
    private final LikeService likeService;
    public LikeController(LikeService likeService){
        this.likeService = likeService;
    }
    @PostMapping
    public ResponseEntity<String> add(LikeRequest likeRequest){
        this.likeService.add(likeRequest);
        return new ResponseEntity<>("Like Added", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<LikeResponse>> getAll(){
        return new ResponseEntity<>(this.likeService.getAll(), HttpStatus.OK);
    }

}
