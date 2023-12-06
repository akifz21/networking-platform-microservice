package com.akifozdemir.userservice.mappers;

import com.akifozdemir.userservice.dtos.FollowRequest;
import com.akifozdemir.userservice.models.Follow;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface FollowMapper {
    @Mappings({
            @Mapping(source = "userId", target = "user.id"),
            @Mapping(source = "followingId", target = "following.id")
            })
    Follow requestToFollow(FollowRequest followRequest);

}
