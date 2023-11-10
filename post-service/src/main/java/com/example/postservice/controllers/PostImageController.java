package com.example.postservice.controllers;

import com.example.postservice.dtos.respones.PostImageResponse;
import com.example.postservice.services.PostImageService;
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

    @PostMapping("/upload")
    public ResponseEntity<PostImageResponse> upload(@RequestParam("image") MultipartFile file,
                                                    @RequestParam UUID postId) throws IOException {
        PostImageResponse postImageResponse = postImageService.upload(file,postId);
        return new ResponseEntity<>(postImageResponse, HttpStatus.OK);
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
    public ResponseEntity<List<byte[]>> getAllImagesByPostId(@PathVariable UUID postId) {
        List<byte[]> images = postImageService.downloadAllImagesByPostId(postId);
        if (images != null && !images.isEmpty()) {
            return ResponseEntity.ok(images);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
