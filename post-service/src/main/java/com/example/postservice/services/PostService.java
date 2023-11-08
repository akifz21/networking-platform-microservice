package com.example.postservice.services;

import com.example.postservice.clients.UserClient;
import com.example.postservice.dtos.requests.PostRequest;
import com.example.postservice.dtos.respones.PostResponse;
import com.example.postservice.dtos.respones.UserResponse;
import com.example.postservice.mappers.PostMapper;
import com.example.postservice.models.Post;
import com.example.postservice.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final UserClient userClient;

    public PostService(PostRepository postRepository,PostMapper postMapper,UserClient userClient){
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.userClient = userClient;
    }

    public void add(PostRequest postRequest){
        UserResponse userResponse = userClient.getUserById(postRequest.userId());
        if (userResponse !=null){
            Post post = this.postMapper.requestToPost(postRequest);
            this.postRepository.save(post);
        }
    }

    public List<PostResponse> getAll(){
        List<Post> posts = this.postRepository.findAll();
        return posts.stream()
                .map(post -> this.postMapper.postToResponse(post))
                .collect(Collectors.toList());
    }

}
