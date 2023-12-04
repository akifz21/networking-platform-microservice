package com.example.postservice.dtos.respones;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.UUID;

public record PostResponse(
        UUID id,
        UUID userId,
        String userFirstName,String userLastName,
        String description,
        LocalDateTime createdDate,
        LocalDateTime lastModifiedDate
) {}
