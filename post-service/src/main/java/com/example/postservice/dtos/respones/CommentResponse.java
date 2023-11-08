package com.example.postservice.dtos.respones;

import java.util.UUID;

public record CommentResponse(
        UUID id,
        String description,
        UUID postId,
        UUID userId
) {}
