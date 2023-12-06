package com.example.postservice.mappers;

import com.example.postservice.dtos.requests.CommentAddRequest;
import com.example.postservice.dtos.respones.CommentResponse;
import com.example.postservice.dtos.respones.UserResponse;
import com.example.postservice.models.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(source = "postId",target = "post.id")
    Comment addRequestToComment(CommentAddRequest commentAddRequest);
    @Mappings({
            @Mapping(source = "user.id",target = "userId"),
            @Mapping(source = "comment.id",target = "id"),
            @Mapping(source = "comment.post.id",target = "postId"),
            @Mapping(source = "user.firstName",target = "userFirstName"),
            @Mapping(source = "user.lastName",target = "userLastName")
    })
    CommentResponse commentToResponse(Comment comment, UserResponse user);
}
