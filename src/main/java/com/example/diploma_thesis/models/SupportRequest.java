package com.example.diploma_thesis.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "supportRequest")
public class SupportRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String userLogin;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String message;

    private LocalDateTime dateTime;

    @PrePersist
    private void init(){dateTime=LocalDateTime.now();}

}
