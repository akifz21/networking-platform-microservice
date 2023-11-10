package com.example.postservice.mappers;

import com.example.postservice.dtos.respones.PostImageResponse;
import com.example.postservice.models.PostImage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostImageMapper {
    @Mapping(target = "postId",source = "post.id")
    PostImageResponse imageToResponse(PostImage postImage);
}
