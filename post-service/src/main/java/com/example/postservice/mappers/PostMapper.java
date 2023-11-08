package com.example.postservice.mappers;

import com.example.postservice.dtos.requests.PostRequest;
import com.example.postservice.dtos.respones.PostResponse;
import com.example.postservice.models.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {

    PostResponse postToResponse(Post post);
    Post requestToPost(PostRequest postRequest);
}
