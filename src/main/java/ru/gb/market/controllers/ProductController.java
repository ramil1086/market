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
//    private static final Function<Product, ProductDto> mapProductToProductDto = p -> new ProductDto(p.getId(), p.getTitle(), p.getPrice(), p.getCategory().getTitle());

    //    GET http://localhost:4444/market/api/v1/products/{id}
    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        return new ProductDto(productService.findById(id));

//        return mapProductToProductDto.apply(productService.findById(id));
    }

    //    GET http://localhost:4444/market/api/v1/products
    @GetMapping
    public Page<ProductDto> findAll(@RequestParam(name = "p", defaultValue = "1") int pageIndex) {
        return productService.findPage(pageIndex-1,10).map(ProductDto::new);
//        return productService.findPage(pageIndex-1,10).map(mapProductToProductDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
    }


    //    POST http://localhost:4444/market/api/v1/products
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
