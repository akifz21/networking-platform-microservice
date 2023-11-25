package com.example.postservice.clients;

import com.example.postservice.dtos.respones.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "users",url = "http://localhost:8000/users")
public interface UserClient {
    @GetMapping("/{id}")
    UserResponse getUserById(@PathVariable UUID id);
}
