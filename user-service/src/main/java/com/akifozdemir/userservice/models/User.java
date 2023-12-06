package com.akifozdemir.userservice.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String firstName;
    private String lastName;
    private String description;
    private String email;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    Set<Follow> followedUsers;

    @OneToMany(mappedBy = "following", cascade = CascadeType.ALL)
    Set<Follow> usersFollowing;

}
