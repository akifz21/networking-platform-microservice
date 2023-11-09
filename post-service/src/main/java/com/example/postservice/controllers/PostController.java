package com.example.postservice.controllers;

import com.example.postservice.dtos.requests.PostRequest;
import com.example.postservice.dtos.respones.PostResponse;
import com.example.postservice.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody PostRequest postRequest){
        this.postService.add(postRequest);
        return new ResponseEntity<>("Post added", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAll(){
        return new ResponseEntity<>(this.postService.getAllPostsWithUserDetails(), HttpStatus.OK);
    }
}
