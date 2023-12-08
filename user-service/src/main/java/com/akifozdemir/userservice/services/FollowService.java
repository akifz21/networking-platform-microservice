package com.akifozdemir.userservice.services;

import com.akifozdemir.userservice.dtos.UserResponse;
import com.akifozdemir.userservice.dtos.follow.FollowRequest;
import com.akifozdemir.userservice.mappers.FollowMapper;
import com.akifozdemir.userservice.mappers.UserMapper;
import com.akifozdemir.userservice.models.Follow;
import com.akifozdemir.userservice.repositories.FollowRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FollowService {
    private final FollowRepository followRepository;
    private final FollowMapper followMapper;
    private final UserMapper userMapper;
    public FollowService(FollowRepository followRepository,FollowMapper followMapper,UserMapper userMapper){
        this.followRepository = followRepository;
        this.followMapper = followMapper;
        this.userMapper = userMapper;
    }

    public void follow(FollowRequest followRequest){
        Follow follow = this.followMapper.requestToFollow(followRequest);
        this.followRepository.save(follow);
    }

    public void unfollow(FollowRequest followRequest){
        this.followRepository.deleteByUser_IdAndFollowing_Id(followRequest.userId(),followRequest.followingId());
    }

    public boolean isFollowing(UUID userId,UUID followingId){
        return this.followRepository.existsByUser_IdAndFollowing_Id(userId,followingId);
    }

    public List<UserResponse> getUserFollowing(UUID userId){
        List<Follow> follows = this.followRepository.findByUser_Id(userId);
        return follows.stream()
                .map(Follow::getFollowing)
                .map(user -> this.userMapper.userToResponse(user))
                .collect(Collectors.toList());
    }

    public List<UserResponse> getUserFollowers(UUID userId){
        List<Follow> follows = this.followRepository.findByFollowing_Id(userId);
        return follows.stream()
                .map(Follow::getUser)
                .map(user -> this.userMapper.userToResponse(user))
                .collect(Collectors.toList());

    }

    @Transactional
    public String toggle(FollowRequest followRequest){
        if (isFollowing(followRequest.userId(),followRequest.followingId())==false){
            follow(followRequest);
            return "User has followed";
        }
        unfollow(followRequest);
        return "User has unfollowed";
    }


}
