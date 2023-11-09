package com.example.postservice.mappers;

import com.example.postservice.dtos.requests.PostRequest;
import com.example.postservice.dtos.respones.PostResponse;
import com.example.postservice.dtos.respones.UserResponse;
import com.example.postservice.models.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mappings({
            @Mapping(source = "user.id", target = "userId"),
            @Mapping(source = "post.id", target = "id"),
            @Mapping(source = "user.firstName",target = "userFirstName"),
            @Mapping(source = "user.lastName",target = "userLastName")

    })
    PostResponse postToResponse(Post post, UserResponse user);

    Post requestToPost(PostRequest postRequest);
}
