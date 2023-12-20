package com.akifozdemir.companyservice.client;

import com.akifozdemir.companyservice.dtos.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "users",url = "http://localhost:8000/users")
public interface UserClient {
    @GetMapping("/{id}")
    UserResponse getUserById(@PathVariable UUID id);

}
