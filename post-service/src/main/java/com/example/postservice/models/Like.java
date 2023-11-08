package com.example.postservice.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@Entity
@Table(name = "likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID userId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}

