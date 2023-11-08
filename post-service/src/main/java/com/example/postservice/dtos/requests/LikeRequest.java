package com.example.postservice.dtos.requests;

import java.util.UUID;

public record LikeRequest(UUID postId,UUID userId) {
}
