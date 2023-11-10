package com.example.postservice.repositories;

import com.example.postservice.models.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PostImageRepository extends JpaRepository<PostImage, UUID> {
    Optional<PostImage> findPostImageByPost_Id(UUID postId);
}
