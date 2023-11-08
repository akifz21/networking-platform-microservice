package com.akifozdemir.userservice.mappers;

import com.akifozdemir.userservice.dtos.UserRequest;
import com.akifozdemir.userservice.dtos.UserResponse;
import com.akifozdemir.userservice.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User requestToUser(UserRequest userRequest);
    UserResponse userToResponse(User user);
}
