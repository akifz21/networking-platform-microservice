package com.example.postservice.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID userId;
    private String description;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
