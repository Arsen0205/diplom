package com.example.diploma_thesis.controller;


import com.example.diploma_thesis.dto.request.RegisterSupplierDtoRequest;
import com.example.diploma_thesis.dto.response.Response;
import com.example.diploma_thesis.service.SupplierService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
@AllArgsConstructor
public class SupplierController {
    private SupplierService supplierService;

    @PostMapping("/supplier/register")
    public Response registerSupplier(RegisterSupplierDtoRequest request){
        supplierService.registerSupplier(request);
        return new Response("Вы успешно зарегистрировались!");
    }
}
