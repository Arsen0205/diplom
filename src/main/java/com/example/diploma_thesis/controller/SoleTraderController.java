package com.example.diploma_thesis.controller;


import com.example.diploma_thesis.dto.request.LoginDtoRequest;
import com.example.diploma_thesis.dto.request.RegisterSoleTraderDtoRequest;
import com.example.diploma_thesis.dto.response.Response;
import com.example.diploma_thesis.service.SoleTraderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SoleTraderController {
    private SoleTraderService soleTraderService;

    @PostMapping("/soleTrader/register")
    public Response registerSoleTrader(@Valid @RequestBody RegisterSoleTraderDtoRequest request){
        soleTraderService.registerSoleTrader(request);
        return new Response("Вы успешно зарегистрировались");
    }

    @PostMapping("/soleTrader/login")
    public Response loginSoleTrader(@Valid @RequestBody LoginDtoRequest request){
        soleTraderService.loginSoleTrader(request);
        return new Response("Вход выполнен успешно");
    }
}
