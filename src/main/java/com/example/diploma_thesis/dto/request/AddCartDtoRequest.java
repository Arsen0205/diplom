package com.example.diploma_thesis.dto.request;


import lombok.Data;

@Data
public class AddCartDtoRequest {
    private Long userId;
    private Long productId;
    private int quantity;
}
