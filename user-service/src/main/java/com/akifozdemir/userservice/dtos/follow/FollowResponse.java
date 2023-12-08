package com.akifozdemir.userservice.dtos.follow;

import java.util.UUID;

public record FollowResponse (UUID id,UUID userId,UUID followingId,String userName,String followingName){
}
