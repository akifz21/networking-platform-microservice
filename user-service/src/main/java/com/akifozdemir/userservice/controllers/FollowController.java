package com.akifozdemir.userservice.controllers;

import com.akifozdemir.userservice.dtos.FollowRequest;
import com.akifozdemir.userservice.services.FollowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/follows")
public class FollowController {
    private final FollowService followService;
    public FollowController(FollowService followService){
        this.followService = followService;
    }
    public ResponseEntity<String> follow(@RequestBody FollowRequest followRequest){
        this.followService.follow(followRequest);
        return ResponseEntity.ok().body("User has followed");
    }

    public ResponseEntity<String> unfollow(@RequestBody FollowRequest followRequest){
        this.followService.unfollow(followRequest);
        return ResponseEntity.ok().body("User has unfollowed");
    }

    public ResponseEntity<String> toggle(@RequestBody FollowRequest followRequest){
        return ResponseEntity.ok().body(this.followService.toggle(followRequest));
    }
}
