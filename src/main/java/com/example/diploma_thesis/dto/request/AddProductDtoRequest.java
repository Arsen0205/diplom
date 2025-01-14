package com.example.diploma_thesis.dto.request;


import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    @NotNull(message = "Данное поле должно быть заполнено")
    private List<MultipartFile> images;
    private String login;
}
