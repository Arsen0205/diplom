package com.example.diploma_thesis.controller;

import com.example.diploma_thesis.dto.response.Response;
import com.example.diploma_thesis.models.Category;
import com.example.diploma_thesis.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CategoryController {
    private CategoryRepository categoryRepository;

    @PostMapping("/categories")
    public Response addCategories(@RequestBody Category category){
        ResponseEntity.ok(categoryRepository.save(category));

        return new Response("Вы добавили категорию товаров");
    }
}
