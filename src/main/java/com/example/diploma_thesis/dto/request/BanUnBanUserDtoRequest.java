package com.example.diploma_thesis.dto.request;


import com.example.diploma_thesis.models.enums.Role;
import lombok.Data;

@Data
public class BanUnBanUserDtoRequest {
    private String login;
    private Role newRole;
}
