package com.example.diploma_thesis.dto.request;

import com.example.diploma_thesis.models.enums.Role;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;

import java.util.HashSet;
import java.util.Set;

@Data
public class RegisterUserDtoRequest {
    @NotNull(message = "Данное поле не может быть пустым")
    @Size(min = 2, max = 50, message = "Логин должен быть длиной от 2-х до 50-ти символов")
    private String login;
    @NotNull(message = "Данное поле не может быть пустым")
    private String password;
    @NotNull(message = "Данное поле не может быть пустым")
    private Set<Role> role = new HashSet<>();
}
