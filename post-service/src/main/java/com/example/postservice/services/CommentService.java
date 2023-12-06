package com.example.postservice.services;

import com.example.postservice.clients.UserClient;
import com.example.postservice.dtos.requests.CommentAddRequest;
import com.example.postservice.dtos.respones.CommentResponse;
import com.example.postservice.dtos.respones.UserResponse;
import com.example.postservice.exceptions.FetchException;
import com.example.postservice.mappers.CommentMapper;
import com.example.postservice.models.Comment;
import com.example.postservice.repositories.CommentRepository;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    private final UserClient userClient;

    public CommentService(CommentRepository commentRepository,
                          CommentMapper commentMapper,UserClient userClient){
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.userClient = userClient;
    }

    public void add(CommentAddRequest commentAddRequest){
        Comment comment = this.commentMapper.addRequestToComment(commentAddRequest);
        this.commentRepository.save(comment);
    }
    public List<CommentResponse> getByPost(UUID postId){
       try{
           List<Comment> comments = this.commentRepository.findByPost_Id(postId);
           List<UUID> userIds = comments.stream().
                   map(Comment::getUserId)
                   .distinct()
                   .collect(Collectors.toList());

           Map<UUID,UserResponse> users = this.userClient.getUsersByIds(userIds)
                   .stream()
                   .collect(Collectors.toMap(UserResponse::getId, Function.identity()));

          return comments.stream().map(comment -> {
              UserResponse userResponse = users.get(comment.getUserId());
              return this.commentMapper.commentToResponse(comment,userResponse);
          }).collect(Collectors.toList());

       }catch (FeignException e){
           if (e instanceof FeignException.NotFound) throw new FetchException("User not found");
           throw new FetchException();
       }
    }


    public List<CommentResponse> getByUser (UUID userId){
      try {
          UserResponse userResponse = this.userClient.getUserById(userId);
          List<Comment> comments = this.commentRepository.findByUserId(userId);
          return comments.stream()
                  .map(comment -> this.commentMapper.commentToResponse(comment,userResponse))
                  .collect(Collectors.toList());
      }catch (FeignException e){
            if (e instanceof FeignException.NotFound) throw new FetchException("User not found");
            throw new FetchException();
      }
    }
}
