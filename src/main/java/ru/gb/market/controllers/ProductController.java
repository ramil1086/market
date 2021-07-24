package ru.gb.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.market.dto.ProductDto;
import ru.gb.market.models.Category;
import ru.gb.market.models.Product;
import ru.gb.market.services.CategoryService;
import ru.gb.market.services.ProductService;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    //    GET http://localhost:4444/market/products/{id}
    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        return new ProductDto(productService.findById(id));
    }

    //    GET http://localhost:4444/market/products
    @GetMapping
    public Page<ProductDto> findAll(@RequestParam(name = "p", defaultValue = "1") int pageIndex) {
        Page<Product> productPage = productService.findPage(pageIndex-1,10);
        Page<ProductDto> productDtoPage = productPage.map(product -> new ProductDto(product));
        return  productDtoPage;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
    }


    //    POST http://localhost:4444/market/products
//    {
//        "id": 31,
//            "title": "Product31",
//            "price": 310,
//            "categoryTitle": "Food"
//    }
    @PostMapping
    public ProductDto createNewProduct(@RequestBody ProductDto newProductDto) {
        Product product = new Product();
        product.setTitle(newProductDto.getTitle());
        product.setPrice(newProductDto.getPrice());
        Category category = categoryService.findByTitle(newProductDto.getCategoryTitle());
        product.setCategory(category);
        return new ProductDto(productService.save(product));
    }


}
