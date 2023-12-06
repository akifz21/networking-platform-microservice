package com.example.postservice.controllers;

import com.example.postservice.dtos.requests.LikeRequest;
import com.example.postservice.dtos.respones.LikeResponse;
import com.example.postservice.models.Like;
import com.example.postservice.services.LikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/likes")
public class LikeController {
    private final LikeService likeService;
    public LikeController(LikeService likeService){
        this.likeService = likeService;
    }
    @PostMapping
    public ResponseEntity<String> add(@RequestBody LikeRequest likeRequest){
        this.likeService.add(likeRequest);
        return new ResponseEntity<>("Like Added", HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam UUID postId,@RequestParam UUID userId){
        this.likeService.delete(postId,userId);
        return ResponseEntity.ok().body("Deleted");
    }

    @PostMapping("/toggle")
    public ResponseEntity<String> toggle(@RequestBody LikeRequest likeRequest){
        this.likeService.toggleLike(likeRequest);
        return ResponseEntity.ok().body("Successfully");
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<LikeResponse>> getByPost(@PathVariable UUID postId){
        return ResponseEntity.ok().body(this.likeService.getByPost(postId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LikeResponse>> getByUser(@PathVariable UUID userId){
        return ResponseEntity.ok().body(this.likeService.getByUser(userId));
    }

    @GetMapping("/count/{postId}")
    public ResponseEntity<Long> getLikeCountByPost(@PathVariable UUID postId){
        return ResponseEntity.ok().body(this.likeService.getCountByPost(postId));
    }

    @GetMapping("/check")
    public ResponseEntity<Boolean> checkIfUserLikedPost(@RequestParam UUID userId,@RequestParam UUID postId){
        return ResponseEntity.ok().body(this.likeService.hasUserLikedPost(userId,postId));
    }

    @GetMapping
    public ResponseEntity<List<LikeResponse>> getAll(){
        return new ResponseEntity<>(this.likeService.getAll(), HttpStatus.OK);
    }

}
