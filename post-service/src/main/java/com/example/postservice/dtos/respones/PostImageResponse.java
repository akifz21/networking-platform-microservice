package com.example.postservice.dtos.respones;

import java.util.List;
import java.util.UUID;

public record PostImageResponse(UUID id, UUID postId, String name, String type) {
}
