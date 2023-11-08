package com.example.postservice.dtos.respones;

import java.util.UUID;

public record UserResponse(UUID id,String firstName,String lastName) {
}
