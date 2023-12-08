package com.akifozdemir.userservice.repositories;

import com.akifozdemir.userservice.models.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FollowRepository extends JpaRepository<Follow, UUID> {
    void deleteByUser_IdAndFollowing_Id(UUID userId, UUID followingId);

    boolean existsByUser_IdAndFollowing_Id(UUID userId, UUID followingId);

    List<Follow> findByUser_Id(UUID userId);
    List<Follow> findByFollowing_Id(UUID userId);
}
