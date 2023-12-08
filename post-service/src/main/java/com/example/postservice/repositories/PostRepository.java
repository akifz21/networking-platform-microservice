package com.example.postservice.repositories;

import com.example.postservice.models.Post;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
    List<Post> findByUserIdOrderByCreatedDateDesc(UUID userId);

    List<Post> findAllByOrderByCreatedDateDesc();
}
