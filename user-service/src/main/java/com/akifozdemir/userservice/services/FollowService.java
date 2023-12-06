package com.akifozdemir.userservice.services;

import com.akifozdemir.userservice.dtos.FollowRequest;
import com.akifozdemir.userservice.mappers.FollowMapper;
import com.akifozdemir.userservice.models.Follow;
import com.akifozdemir.userservice.repositories.FollowRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FollowService {
    private final FollowRepository followRepository;
    private final FollowMapper followMapper;
    public FollowService(FollowRepository followRepository,FollowMapper followMapper){
        this.followRepository = followRepository;
        this.followMapper = followMapper;
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

    public String toggle(FollowRequest followRequest){
        if (isFollowing(followRequest.userId(),followRequest.followingId())==false){
            follow(followRequest);
            return "User has followed";
        }
        unfollow(followRequest);
        return "User has unfollowed";
    }


}
