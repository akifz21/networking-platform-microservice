package com.akifozdemir.userservice.controllers;

import com.akifozdemir.userservice.dtos.UserRequest;
import com.akifozdemir.userservice.dtos.UserResponse;
import com.akifozdemir.userservice.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    public UserController(UserService userService,AuthenticationManager authenticationManager){
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable UUID id){
        return ResponseEntity.ok().body(this.userService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll(){
        return ResponseEntity.ok().body(this.userService.getAll());
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRequest userRequest){
        this.userService.add(userRequest);
        return ResponseEntity.ok().body("User Added "+userRequest.toString());
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserRequest userRequest){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userRequest.email(),userRequest.password()));
        if (authentication.isAuthenticated()){
            return ResponseEntity.ok().body(this.userService.generateToken(userRequest.email()));
        }
        throw new RuntimeException("Invalid access");
    }
    @PostMapping("/token/{token}")
    public String validate(@PathVariable String token){
        userService.validateToken(token);
        return "Validate";
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody UserRequest userRequest,@PathVariable UUID id){
        this.userService.update(userRequest,id);
        return ResponseEntity.ok().body("User Updated");
    }

}
