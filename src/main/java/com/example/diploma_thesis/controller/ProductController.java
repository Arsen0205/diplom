package com.example.diploma_thesis.controller;


import com.example.diploma_thesis.dto.request.AddProductDtoRequest;
import com.example.diploma_thesis.dto.response.Response;
import com.example.diploma_thesis.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@AllArgsConstructor
public class ProductController {
    private ProductService productService;

    @PostMapping("/product/add")
    public Response addProduct(@Valid @ModelAttribute AddProductDtoRequest request) throws IOException {
        productService.addProduct(request);

        return new Response("Товар успешно добавлен");
    }
}
