package com.akifozdemir.userservice.controllers;

import com.akifozdemir.userservice.dtos.UserRequest;
import com.akifozdemir.userservice.dtos.UserResponse;
import com.akifozdemir.userservice.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable UUID id){
        return ResponseEntity.ok().body(this.userService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll(){
        return ResponseEntity.ok().body(this.userService.getAll());
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody UserRequest userRequest){
        this.userService.add(userRequest);
        return ResponseEntity.ok().body("User Added"+userRequest.toString());
    }

}
