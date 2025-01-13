package com.example.diploma_thesis.models;


import com.example.diploma_thesis.models.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="sole_trader")
public class SoleTrader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="surname")
    private String surname;

    @Column(name="login", unique = true)
    private String login;

    @Column(name="email", unique = true)
    private String email;

    @Column(name="OGRNIP", unique = true)
    private String ogrnip;

    @Column(name="active")
    private boolean active;

    @Column(name="registry_entry_number", unique = true)
    private String registryEntryNumber;

    @Column(name="date_of_entry_in_the_registry")
    private String dateOfEntryInTheRegistry;

    @Column(name="phone_number", unique = true)
    private String phoneNumber;

    @Column(name="address")
    private String address;

    @Column(name="field_of_activity")
    private String fieldOfActivity;

    @Column(name="bank_requisites", unique = true)
    private String bankRequisites;

    @Column(name="password")
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="user_role", joinColumns = @JoinColumn(name = "user_login", referencedColumnName = "login"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    @Column(name="date_of_created")
    private LocalDateTime dateOfCreated;

    @PrePersist
    private void init(){dateOfCreated=LocalDateTime.now();}
}
