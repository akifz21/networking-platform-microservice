package com.example.postservice.controllers;

import com.example.postservice.dtos.respones.PostImageResponse;
import com.example.postservice.services.PostImageService;
import feign.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/post-images")
public class PostImageController {
    private final PostImageService postImageService;

    public PostImageController(PostImageService postImageService) {
        this.postImageService = postImageService;
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<?> download(@PathVariable UUID id){
        byte[] image = postImageService.download(id);
        if (image!=null){
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

    }

    @GetMapping("/{postId}/images")
    public ResponseEntity<List<PostImageResponse>> getAllImagesByPostId(@PathVariable UUID postId) {
        List<PostImageResponse> images = postImageService.downloadAllImagesByPostId(postId);
        return ResponseEntity.ok(images);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id){
        this.postImageService.delete(id);
        return ResponseEntity.ok().body("Image deleted");
    }
}
