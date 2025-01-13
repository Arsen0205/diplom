package com.example.diploma_thesis.dto.request;


import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class AddProductDtoRequest {
    @NotNull(message = "Данное поле должно быть заполнено")
    private String title;
    @NotNull(message = "Данное поле должно быть заполнено")
    private String description;
    @NotNull(message = "Данное поле должно быть заполнено")
    private int quantity;
    @NotNull(message = "Данное поле должно быть заполнено")
    private int price;
    @NotNull(message = "Данное поле должно быть заполнено")
    private String city;
}
