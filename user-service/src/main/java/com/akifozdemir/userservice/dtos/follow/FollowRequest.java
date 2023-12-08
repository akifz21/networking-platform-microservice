package com.akifozdemir.userservice.dtos.follow;


import java.util.UUID;

public record FollowRequest(UUID userId,UUID followingId) {
}
