package com.example.postservice.services;

import com.example.postservice.clients.UserClient;
import com.example.postservice.dtos.requests.PostRequest;
import com.example.postservice.dtos.requests.PostUpdateRequest;
import com.example.postservice.dtos.respones.PostResponse;
import com.example.postservice.dtos.respones.UserResponse;
import com.example.postservice.exceptions.FetchException;
import com.example.postservice.mappers.PostMapper;
import com.example.postservice.models.Post;
import com.example.postservice.repositories.PostRepository;
import feign.FeignException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final UserClient userClient;
    private final PostImageService postImageService;

    public PostService(PostRepository postRepository,
                       PostMapper postMapper,
                       UserClient userClient,PostImageService postImageService){
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.userClient = userClient;
        this.postImageService = postImageService;
    }

    @Transactional
    public void add(PostRequest postRequest,MultipartFile[] files){
            try {
                UserResponse userResponse = userClient.getUserById(postRequest.userId());
                Post post = this.postMapper.requestToPost(postRequest);
                Post addedPost =  this.postRepository.save(post);
                if (files != null) {
                    for (MultipartFile file : files) {
                        if (!file.isEmpty()) this.postImageService.upload(file, addedPost);
                    }
                }
            }catch (Exception e){
                if (e instanceof FeignException.NotFound){
                    throw new FetchException("User Not Found");
                }
                throw new RuntimeException(e.getMessage());
            }
    }

    public List<PostResponse> getByUser(UUID userId){
            try {
                UserResponse userResponse = userClient.getUserById(userId);
                List<Post> posts = this.postRepository.findByUserIdOrderByCreatedDateDesc(userId);
                return posts
                        .stream()
                        .map(post -> this.postMapper.postToResponse(post,userResponse))
                        .collect(Collectors.toList());
            }catch (FeignException e){
                if (e instanceof FeignException.NotFound){
                    throw new FetchException("User Not Found");
                }
                throw new FetchException(e.getMessage());
            }
    }

    public List<PostResponse> getAllPostsWithUserDetails() {
      try {
          List<Post> posts = this.postRepository.findAllByOrderByCreatedDateDesc();
          List<UUID> userIds = posts.stream()
                  .map(Post::getUserId)
                  .distinct()
                  .collect(Collectors.toList());
          Map<UUID, UserResponse> userMap = userClient.getUsersByIds(userIds)
                  .stream()
                  .collect(Collectors.toMap(UserResponse::getId, Function.identity()));

          return posts.stream().map(post -> {
              UserResponse userResponse = userMap.get(post.getUserId());
              return postMapper.postToResponse(post, userResponse);
          }).collect(Collectors.toList());

      }catch (FeignException e){
          throw new FetchException(e.getMessage());
      }
    }

    public void update(PostUpdateRequest postUpdateRequest, UUID id){
        this.postRepository.
                findById(id).
                ifPresent(post -> post.setDescription(postUpdateRequest.description()));
    }
    public void delete(UUID id){
        this.postRepository.deleteById(id);
    }
    public Post getById(UUID id){
        return this.postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }
}
