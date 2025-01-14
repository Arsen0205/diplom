package com.example.diploma_thesis.controller;


import com.example.diploma_thesis.dto.request.LoginDtoRequest;
import com.example.diploma_thesis.dto.request.RegisterSupplierDtoRequest;
import com.example.diploma_thesis.dto.response.Response;
import com.example.diploma_thesis.service.SupplierService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SupplierController {
    private SupplierService supplierService;

    @PostMapping("/supplier/register")
    public Response registerSupplier(@Valid @RequestBody RegisterSupplierDtoRequest request){
        supplierService.registerSupplier(request);
        System.out.println(request);
        return new Response("Вы успешно зарегистрировались!");
    }

    @PostMapping("/supplier/login")
    public Response loginUser(LoginDtoRequest request){
        supplierService.loginSupplier(request);
        return new Response("Вход выполнен успешно");
    }
}
