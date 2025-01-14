package com.example.diploma_thesis.models;


import com.example.diploma_thesis.models.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name="suppliers")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="company", unique = true)
    private String company;

    @Column(name="name")
    private String name;

    @Column(name="surname")
    private String surname;

    @Column(name="email", unique = true)
    private String email;

    @Column(name="login", unique = true)
    private String login;

    @Column(name="phone_number", unique = true)
    private String phoneNumber;

    @Column(name="address")
    private String address;

    @Column(name="INN", unique = true)
    private String inn;

    @Column(name="registration_number", unique = true)
    private String registrationNumber;

    @Column(name="description", columnDefinition = "text")
    private String description;

    @Column(name="password")
    private String password;

    @Column(name="active")
    private boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name="user_role", joinColumns = @JoinColumn(name = "user_login", referencedColumnName = "login"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    @Column(name="date_of_created")
    private LocalDateTime dateOfCreated;

    @PrePersist
    private void init(){dateOfCreated=LocalDateTime.now();}

}
