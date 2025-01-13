package com.example.diploma_thesis.models;


import com.example.diploma_thesis.models.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="user_role", joinColumns = @JoinColumn(name = "user_login", referencedColumnName = "login"))
    @Enumerated(EnumType.STRING)
    private Set<Role> role = new HashSet<>();

    @Column(name="date_of_created")
    private LocalDateTime dareOfCreated;


    @PrePersist
    private void init(){dareOfCreated=LocalDateTime.now();}
}
