package com.example.postservice.dtos.requests;

import java.util.UUID;

public record PostRequest(String title, String description, UUID userId) {
}
