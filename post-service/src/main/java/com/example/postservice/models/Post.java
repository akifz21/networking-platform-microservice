package com.example.postservice.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;
@Data
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID userId;
    private String title;
    private String description;

    @OneToMany(mappedBy = "post")
    private Set<Comment> comments;
    @OneToMany(mappedBy = "post")
    private Set<Like> likes;

}
