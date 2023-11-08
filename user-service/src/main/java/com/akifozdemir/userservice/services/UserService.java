package com.akifozdemir.userservice.services;

import com.akifozdemir.userservice.dtos.UserRequest;
import com.akifozdemir.userservice.dtos.UserResponse;
import com.akifozdemir.userservice.exceptions.UserNotFoundException;
import com.akifozdemir.userservice.mappers.UserMapper;
import com.akifozdemir.userservice.models.User;
import com.akifozdemir.userservice.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository,UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public void add(UserRequest userRequest){
        User user = userMapper.requestToUser(userRequest);
        this.userRepository.save(user);
    }

    public UserResponse getById(UUID id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));

        return userMapper.userToResponse(user);
    }

    public List<UserResponse> getAll(){
        List<User> users = this.userRepository.findAll();
        return users.stream()
                .map(user -> this.userMapper.userToResponse(user))
                .collect(Collectors.toList());
    }

}
