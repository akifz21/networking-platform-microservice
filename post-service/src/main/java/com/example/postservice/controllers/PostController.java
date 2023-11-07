package com.example.postservice.controllers;

import com.example.postservice.models.Post;
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
    public ResponseEntity<String> add(@RequestBody Post post){
        this.postService.add(post);
        return new ResponseEntity<>("Post added", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAll(){
        return new ResponseEntity<List<Post>>(this.postService.getAll(), HttpStatus.OK);
    }
}
