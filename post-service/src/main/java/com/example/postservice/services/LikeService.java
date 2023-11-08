package com.example.postservice.services;

import com.example.postservice.dtos.requests.LikeRequest;
import com.example.postservice.dtos.respones.LikeResponse;
import com.example.postservice.mappers.LikeMapper;
import com.example.postservice.models.Like;
import com.example.postservice.repositories.LikeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final LikeMapper likeMapper;
    public LikeService(LikeRepository likeRepository,LikeMapper likeMapper){
        this.likeRepository = likeRepository;
        this.likeMapper = likeMapper;
    }
    public void add(LikeRequest likeRequest){
        Like like = this.likeMapper.requestToLike(likeRequest);
        this.likeRepository.save(like);
    }

    public List<LikeResponse> getAll( ){
        List<Like> likes = this.likeRepository.findAll();
        return likes.stream()
                .map(like -> this.likeMapper.likeToResponse(like))
                .collect(Collectors.toList());
    }



}
