package com.example.postservice.dtos.respones;

import java.util.UUID;

public record PostResponse(UUID id,UUID userId,String title,String description) {
}
