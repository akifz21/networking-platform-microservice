package com.example.postservice.services;

import com.example.postservice.models.Post;
import com.example.postservice.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    public void add(Post post){
        this.postRepository.save(post);
    }

    public List<Post> getAll(){
        return this.postRepository.findAll();
    }

}
