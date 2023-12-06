package com.akifozdemir.userservice.dtos;


import java.util.UUID;

public record FollowRequest(UUID userId,UUID followingId) {
}
