package com.akifozdemir.userservice.services;

import com.akifozdemir.userservice.dtos.LoginRequest;
import com.akifozdemir.userservice.dtos.UserRequest;
import com.akifozdemir.userservice.dtos.UserResponse;
import com.akifozdemir.userservice.exceptions.UserNotFoundException;
import com.akifozdemir.userservice.mappers.UserMapper;
import com.akifozdemir.userservice.models.User;
import com.akifozdemir.userservice.repositories.UserRepository;
import io.jsonwebtoken.Jwt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository,
                       UserMapper userMapper,
                       PasswordEncoder passwordEncoder,JwtService jwtService){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public void add(UserRequest userRequest){
        User user = userMapper.requestToUser(userRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.userRepository.save(user);
    }

    public String generateToken(LoginRequest loginRequest){
        User user = this.userRepository.findByEmail(loginRequest.email())
                .orElseThrow(()->new UserNotFoundException());
        return jwtService.generateToken(user);
    }

    public UserResponse getById(UUID id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));

        return userMapper.userToResponse(user);
    }

    public List<UserResponse> getByIds(List<UUID> ids){
        List<User> users = userRepository.findAllByIdIn(ids);
        return users.stream().map(user ->
                this.userMapper.userToResponse(user)).collect(Collectors.toList());
    }

    public List<UserResponse> getAll(){
        List<User> users = this.userRepository.findAll();
        return users.stream()
                .map(user -> this.userMapper.userToResponse(user))
                .collect(Collectors.toList());

    }

}
