package com.example.postservice.services;

import com.example.postservice.dtos.respones.PostImageResponse;
import com.example.postservice.models.Post;
import com.example.postservice.models.PostImage;
import com.example.postservice.repositories.PostImageRepository;
import com.example.postservice.utils.ImageUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostImageService {

    private final PostImageRepository postImageRepository;

    public PostImageService(PostImageRepository postImageRepository){
        this.postImageRepository = postImageRepository;
    }

    public void upload(MultipartFile file, Post post) throws IOException {
        PostImage postImage = new PostImage();
        postImage.setName(file.getOriginalFilename());
        postImage.setType(file.getContentType());
        postImage.setData(ImageUtil.compressImage(file.getBytes()));
        postImage.setPost(post);
        postImageRepository.save(postImage);
    }

    public byte[] download(UUID id){
        Optional<PostImage> postImage = postImageRepository.findById(id);
        if (postImage.isPresent()){
            return ImageUtil.decompressImage(postImage.get().getData());
        }
        return null;
    }

    public List<PostImageResponse> downloadAllImagesByPostId(UUID postId) {
        List<PostImage> postImages = postImageRepository.findAllPostImageByPost_Id(postId);
        return postImages.stream().map(image -> new PostImageResponse(
                image.getId(),image.getPost().getId(),image.getName(),
                image.getType()))
                .collect(Collectors.toList());
    }
}
