package com.akifozdemir.userservice.dtos;

public record UserUpdateRequest(String firstName,String lastName,
                                String email,String description) {
}
