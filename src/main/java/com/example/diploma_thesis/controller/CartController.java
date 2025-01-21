package com.example.diploma_thesis.controller;


import com.example.diploma_thesis.dto.request.AddCartDtoRequest;
import com.example.diploma_thesis.dto.response.Response;
import com.example.diploma_thesis.service.CartService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CartController {
    private CartService cartService;

    @PostMapping("/cart/add")
    public Response addCart(@Valid @RequestBody AddCartDtoRequest request){
        cartService.addCart(request);

        return new Response("Вы добавили товар");
    }
}
