package com.example.diploma_thesis.dto.request;

import com.example.diploma_thesis.models.enums.Role;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class RegisterSoleTraderDtoRequest {
    @NotNull(message = "Данное поле не может быть пустым")
    private String name;
    @NotNull(message = "Данное поле не может быть пустым")
    private String surname;
    @NotNull(message = "Данное поле не может быть пустым")
    @Size(min = 2, max = 50, message = "Логин должен быть длиной от 2-х до 50-ти символов")
    private String login;
    @NotNull(message = "Данное поле не может быть пустым")
    @Size(min=8, max = 50, message = "Пароль должен быть длиной от 8-ми до 50-ти символов")
    private String password;
    @NotNull(message = "Данное поле не может быть пустым")
    private String email;
    @NotNull(message = "Данное поле не может быть пустым")
    private String ogrnip;
    @NotNull(message = "Данное поле не может быть пустым")
    private String registryEntryNumber;
    @NotNull(message = "Данное поле не может быть пустым")
    private String dateOfEntryInTheRegistry;
    @NotNull(message = "Данное поле не может быть пустым")
    private String phoneNumber;
    @NotNull(message = "Данное поле не может быть пустым")
    private String address;
    @NotNull(message = "Данное поле не может быть пустым")
    private String fieldOfActivity;
    @NotNull(message = "Данное поле не может быть пустым")
    private String bankRequisites;
    @NotNull(message = "Данное поле не может быть пустым")
    private Set<Role> roles = new HashSet<>();
}
