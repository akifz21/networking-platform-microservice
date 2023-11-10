package com.example.postservice.services;

import com.example.postservice.dtos.respones.PostImageResponse;
import com.example.postservice.mappers.PostImageMapper;
import com.example.postservice.models.PostImage;
import com.example.postservice.repositories.PostImageRepository;
import com.example.postservice.utils.ImageUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostImageService {

    private final PostImageRepository postImageRepository;
    private final PostService postService;
    private final PostImageMapper postImageMapper;

    public PostImageService(
            PostImageRepository postImageRepository,PostService postService,PostImageMapper postImageMapper){
        this.postImageRepository = postImageRepository;
        this.postService = postService;
        this.postImageMapper = postImageMapper;
    }

    public PostImageResponse upload(MultipartFile file, UUID postId) throws IOException {
        PostImage postImage = new PostImage();
        postImage.setName(file.getOriginalFilename());
        postImage.setType(file.getContentType());
        postImage.setData(ImageUtil.compressImage(file.getBytes()));
        postImage.setPost(postService.getById(postId));
        postImageRepository.save(postImage);
        return postImageMapper.imageToResponse(postImage);
    }

    public byte[] download(UUID id){
        Optional<PostImage> postImage = postImageRepository.findById(id);
        if (postImage.isPresent()){
            return ImageUtil.decompressImage(postImage.get().getData());
        }
        return null;
    }

    public List<byte[]> downloadAllImagesByPostId(UUID postId) {
        List<PostImage> postImages = postImageRepository.findAllPostImageByPost_Id(postId);
        return postImages.stream()
                .map(postImage -> ImageUtil.decompressImage(postImage.getData()))
                .collect(Collectors.toList());
    }
}
