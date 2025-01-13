package com.example.diploma_thesis.controller;


import com.example.diploma_thesis.dto.request.LoginDtoRequest;
import com.example.diploma_thesis.dto.request.RegisterUserDtoRequest;
import com.example.diploma_thesis.dto.response.Response;
import com.example.diploma_thesis.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @PostMapping("/register")
    public Response registerUser(@Valid @RequestBody RegisterUserDtoRequest request){
        userService.registerUser(request);
        return new Response("Вы успешно зарегистрировались!");
    }

    @PostMapping("/login")
    public Response loginUser(@Valid @RequestBody LoginDtoRequest request){
        userService.loginUser(request);
        return new Response("Вход выполнен успешно");
    }
}
