package com.example.postservice.services;

import com.example.postservice.dtos.requests.CommentAddRequest;
import com.example.postservice.dtos.respones.CommentResponse;
import com.example.postservice.mappers.CommentMapper;
import com.example.postservice.models.Comment;
import com.example.postservice.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    public CommentService(CommentRepository commentRepository,CommentMapper commentMapper){
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    public void add(CommentAddRequest commentAddRequest){
        Comment comment = this.commentMapper.addRequestToComment(commentAddRequest);
        this.commentRepository.save(comment);
    }
    public List<CommentResponse> getAll(){
        List<Comment> comments = this.commentRepository.findAll();
        return comments.stream()
                .map(comment -> this.commentMapper.commentToResponse(comment))
                .collect(Collectors.toList());
    }


}
