package com.akifozdemir.userservice.repositories;

import com.akifozdemir.userservice.models.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FollowRepository extends JpaRepository<Follow, UUID> {
    void deleteByUser_IdAndFollowing_Id(UUID userId, UUID followingId);

    boolean existsByUser_IdAndFollowing_Id(UUID userId, UUID followingId);

}
