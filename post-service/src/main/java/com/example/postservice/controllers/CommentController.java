package com.example.postservice.controllers;

import com.example.postservice.dtos.requests.CommentAddRequest;
import com.example.postservice.dtos.respones.CommentResponse;
import com.example.postservice.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody CommentAddRequest commentAddRequest){
        this.commentService.add(commentAddRequest);
        return ResponseEntity.ok().body("Comment Added");
    }

    @GetMapping
    public ResponseEntity<List<CommentResponse>> getAll(){
        return ResponseEntity.ok().body(this.commentService.getAll());
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CommentResponse>> getByUser(@PathVariable UUID userId){
        return ResponseEntity.ok().body(this.commentService.getByUser(userId));
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentResponse>> getByPost(@PathVariable UUID postId){
        return ResponseEntity.ok().body(this.commentService.getByPost(postId));
    }

}
