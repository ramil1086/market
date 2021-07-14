package ru.gb.market.controllers;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.gb.market.models.Category;
import ru.gb.market.repositories.CategoryRepository;
import ru.gb.market.services.CategoryService;

@Controller
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("categories/{id}")
    @ResponseBody
    public Category showCategoryById(@PathVariable Long id) {
        return categoryService.findByIdWithProducts(id).get();
    }
}
