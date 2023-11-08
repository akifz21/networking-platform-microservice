package com.akifozdemir.userservice.dtos;

public record UserRequest(
        String firstName,
        String lastName,
        String description,
        String password,
        String email
) {}
