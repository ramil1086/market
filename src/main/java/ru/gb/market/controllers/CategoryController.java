package ru.gb.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.market.dto.CategoryDto;
import ru.gb.market.models.Category;
import ru.gb.market.services.CategoryService;
import ru.gb.market.exceptions.ResourceNotFoundException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    //    GET http://localhost:4444/market/products/{id}
    @GetMapping("/{id}")
    public CategoryDto findById(@PathVariable Long id) {
        Category c = categoryService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found, id: " + id));
        return new CategoryDto(c);
    }



}
