package com.example.postservice.services;

import com.example.postservice.clients.UserClient;
import com.example.postservice.dtos.requests.LikeRequest;
import com.example.postservice.dtos.respones.LikeResponse;
import com.example.postservice.dtos.respones.UserResponse;
import com.example.postservice.exceptions.FetchException;
import com.example.postservice.mappers.LikeMapper;
import com.example.postservice.models.Like;
import com.example.postservice.repositories.LikeRepository;
import feign.FeignException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final LikeMapper likeMapper;
    private final UserClient userClient;
    public LikeService(LikeRepository likeRepository,LikeMapper likeMapper,UserClient userClient){
        this.likeRepository = likeRepository;
        this.likeMapper = likeMapper;
        this.userClient= userClient;
    }
    public void add(LikeRequest likeRequest){
        boolean isLiked = this.likeRepository.existsByUserIdAndPostId(likeRequest.userId(),likeRequest.postId());
        Like like = this.likeMapper.requestToLike(likeRequest);
        if (isLiked == false) this.likeRepository.save(like);
    }

    public void delete(UUID postId,UUID userId){
        this.likeRepository.deleteByPost_IdAndUserId(postId,userId);
    }

    public List<LikeResponse> getAll(){
        List<Like> likes = this.likeRepository.findAll();
        return likes.stream()
                .map(like -> this.likeMapper.likeToResponse(like))
                .collect(Collectors.toList());
    }

    public List<LikeResponse> getByPost(UUID postId){
        List<Like> likes = this.likeRepository.findByPost_Id(postId);
        return likes.stream()
                .map(like -> this.likeMapper.likeToResponse(like))
                .collect(Collectors.toList());
    }

    @Transactional
    public void toggleLike(LikeRequest likeRequest){
        boolean isLiked = this.likeRepository.existsByUserIdAndPostId(likeRequest.userId(),likeRequest.postId());
        Like like = this.likeMapper.requestToLike(likeRequest);
        if (isLiked == false){
            this.likeRepository.save(like);
        }else{
            this.likeRepository.deleteByPost_IdAndUserId(likeRequest.postId(),likeRequest.userId());
        }
    }

    public long getCountByPost(UUID postId){
        return this.likeRepository.countByPost_Id(postId);
    }

    public boolean hasUserLikedPost(UUID userId,UUID postId){
        return this.likeRepository.existsByUserIdAndPostId(userId,postId);
    }

    public List<LikeResponse> getByUser(UUID userId){
        try {
            UserResponse userResponse = userClient.getUserById(userId);

            List<Like> likes = this.likeRepository.findByUserId(userId);
            return likes.stream()
                    .map(like -> this.likeMapper.likeToResponse(like))
                    .collect(Collectors.toList());
        }catch (FeignException e){
            if (e instanceof FeignException.NotFound) throw new FetchException("User Not Found");
            throw new FetchException();
        }
    }
}
