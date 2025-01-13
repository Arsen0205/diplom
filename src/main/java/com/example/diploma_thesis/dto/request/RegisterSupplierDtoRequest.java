package com.example.diploma_thesis.dto.request;


import com.example.diploma_thesis.models.enums.Role;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class RegisterSupplierDtoRequest {
    @NotNull(message = "Данное поле не может быть пустым")
    private String name;
    @NotNull(message = "Данное поле не может быть пустым")
    private String surname;
    @NotNull(message = "Данное поле не может быть пустым")
    @Size(min = 2, max = 50, message = "Логин должен быть длиной от 2-х до 50-ти символов")
    private String login;
    @NotNull(message = "Данное поле не может быть пустым")
    @Size(min = 8, max = 50, message = "Пароль должен быть длиной от 8 до 50 символов")
    private String password;
    @NotNull(message = "Данное поле не может быть пустым")
    private String email;
    @NotNull(message = "Данное поле не может быть пустым")
    @Size(min= 11, max = 12, message = "Номер телефона должен содержать от 11-ти до 12-ти символов")
    private String phoneNumber;
    @NotNull(message = "Данное поле не может быть пустым")
    private String address;
    @NotNull(message = "Данное поле не может быть пустым")
    private String inn;
    @NotNull(message = "Данное поле не может быть пустым")
    private String company;
    @NotNull(message = "Данное поле не может быть пустым")
    private String registrationNumber;
    @NotNull(message = "Данное поле не может быть пустым")
    private String description;
    @NotNull(message = "Данное поле не может быть пустым")
    private Set<Role> role = new HashSet<>();
}
