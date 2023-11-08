package com.example.postservice.controllers;

import com.example.postservice.dtos.requests.CommentAddRequest;
import com.example.postservice.dtos.respones.CommentResponse;
import com.example.postservice.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return new ResponseEntity<>("Comment Added", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CommentResponse>> getAll(){
        return new ResponseEntity<>(this.commentService.getAll(),HttpStatus.OK);
    }

}
