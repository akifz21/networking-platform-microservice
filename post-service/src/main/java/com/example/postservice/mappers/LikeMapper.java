package com.example.postservice.mappers;

import com.example.postservice.dtos.requests.LikeRequest;
import com.example.postservice.dtos.respones.LikeResponse;
import com.example.postservice.models.Like;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface LikeMapper {
    @Mapping(source = "post.id",target = "postId")
    LikeResponse likeToResponse(Like like);
    @Mapping(source = "postId",target = "post.id")
    Like requestToLike(LikeRequest likeRequest);
}
