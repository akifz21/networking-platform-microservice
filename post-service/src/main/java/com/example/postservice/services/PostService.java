package com.example.postservice.services;

import com.example.postservice.clients.UserClient;
import com.example.postservice.dtos.requests.PostRequest;
import com.example.postservice.dtos.respones.PostResponse;
import com.example.postservice.dtos.respones.UserResponse;
import com.example.postservice.exceptions.FetchException;
import com.example.postservice.mappers.PostMapper;
import com.example.postservice.models.Post;
import com.example.postservice.repositories.PostRepository;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
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
            try {
                UserResponse userResponse = userClient.getUserById(postRequest.userId());
                Post post = this.postMapper.requestToPost(postRequest);
                this.postRepository.save(post);
            }catch (FeignException e){
                if (e instanceof FeignException.NotFound){
                    throw new FetchException("User Not Found");
                }
                throw new FetchException("An error occurred during fetch");

            }
    }

    public List<PostResponse> getByUser(UUID userId){
            try {
                UserResponse userResponse = userClient.getUserById(userId);
                List<Post> posts = this.postRepository.findByUserId(userId);
                return posts
                        .stream()
                        .map(post -> this.postMapper.postToResponse(post,userResponse))
                        .collect(Collectors.toList());
            }catch (FeignException e){
                if (e instanceof FeignException.NotFound){
                    throw new FetchException("User Not Found");
                }
                throw new FetchException("An error occurred during fetch");
            }
    }

    public List<PostResponse> getAllPostsWithUserDetails() {
      try {
          List<Post> posts = this.postRepository.findAll();
          return posts.stream().map(post -> {
              UserResponse userResponse = userClient.getUserById(post.getUserId());
              return postMapper.postToResponse(post, userResponse);
          }).collect(Collectors.toList());
      }catch (FeignException e){
          throw new FetchException("An error occurred during fetch");
      }
    }

    public Post getById(UUID id){
        return this.postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }
}
