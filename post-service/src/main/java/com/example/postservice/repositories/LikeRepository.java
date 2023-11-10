package com.example.postservice.repositories;

import com.example.postservice.models.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LikeRepository extends JpaRepository<Like, UUID> {
    List<Like> findByPost_Id(UUID id);
    List<Like> findByUserId(UUID userId);
}
