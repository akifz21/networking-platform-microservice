package com.example.postservice.dtos.respones;

import java.time.LocalDateTime;
import java.util.UUID;

public record CommentResponse(
        UUID id,
        String description,
        UUID postId,
        UUID userId,
        String userFirstName,
        String userLastName,
        LocalDateTime createdDate
) {}
