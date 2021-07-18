package ru.gb.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.market.models.Product;
import ru.gb.market.services.ProductService;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

//    GET http://localhost:4444/market/products/{id}
    @GetMapping("/products/{id}")
    public Product findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    //    GET http://localhost:4444/market/products
    @GetMapping("/products")
    public List<Product> findAll() {
        return productService.findAll();
    }

    @PostMapping("/products/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

    //    GET http://localhost:4444/market/products_page?p=
    @GetMapping("/products_page")
    public Page<Product> findPage(@RequestParam(name = "p") int pageIndex) {
        return productService.findPage(pageIndex-1, 10);
    }

}
