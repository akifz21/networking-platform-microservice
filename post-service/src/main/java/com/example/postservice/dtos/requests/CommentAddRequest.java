package com.example.postservice.dtos.requests;

import java.util.UUID;

public record CommentAddRequest(UUID postId,String description,UUID userId) {
}
