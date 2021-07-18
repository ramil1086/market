package ru.gb.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    //    отображает все продукты
//    GET http://localhost:4444/market
    @GetMapping
    public List<Product> showMainPage() {
        return productService.findAll();
    }

    // добавляет товар
//    GET http://localhost:4444/market/products/add?title=cola&price=100
    @GetMapping("products/add")
    public List<Product> addProductAndShowAllProducts(@RequestParam String title, @RequestParam int price) {
        saveNewProduct(title, price);
        return productService.findAll();
    }

    //    добавляет товар - Работает в паре с GET
    @PostMapping("products/add")
    public String saveNewProduct(@RequestParam String title, @RequestParam int price) {
        productService.saveNewProduct(title, price);
        return "redirect:/";
    }

    //    отображает товар по ID
    // GET http://localhost:4444/market/products/{id}
    @GetMapping("products/{id}")
    public Product showProductInfo(@PathVariable Long id) {
        return productService.findById(id);
    }

    //    удаляет товар по ID
//    GET http://localhost:4444/market/products/delete/{id}
    @GetMapping("products/delete/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
//        return productService.findAll();
    }

    //    ищет товары выше минимальной цены
//    GET http://localhost:4444/market/products/find_by_min_price?min=
    @GetMapping("products/find_by_min_price")
    public List<Product> findByMinPrice(@RequestParam(name = "min") Integer min) {
        return productService.findByMinPrice(min);
    }


    //    ищет товары ниже максимальной цены
//    GET http://localhost:4444/market/products/find_by_max_price?
    @GetMapping("products/find_by_max_price")
    @ResponseBody
    public List<Product> findByMaxPrice(@RequestParam(name = "max") Integer max) {
        return productService.findByMaxPrice(max);
    }

    //    ищет товары с ценой в пределах мин и макс
//    GET http://localhost:4444/market/products/find_by_min_max_price?
    @GetMapping("products/find_by_min_max_price")
    @ResponseBody
    public List<Product> findByMaxPrice(@RequestParam(name = "min") Integer min, @RequestParam(name = "max") Integer max) {
        return productService.findByMinAndMaxPrice(min, max);
    }


//
//    //    отображает основную страницу со всеми продуктами
////    GET http://localhost:4444/market
//    @GetMapping
//    public String showMainPage(Model model) {
//        model.addAttribute("products", productService.findAll());
//        return "index";
//    }
//
//    // переводит на страницу добавления товара
////    GET http://localhost:4444/market/products/add
//    @GetMapping("products/add")
//    public String showAddProductForm() {
//        return "add_product_form";
//    }
//
//    //    добавляет товар через
////    POST
//    @PostMapping("products/add")
//    public String saveNewProduct(@RequestParam String title, @RequestParam int price) {
//        productService.saveNewProduct(title, price);
//        return "redirect:/";
//    }
//
//    //    отображает товар по ID
//    // GET http://localhost:4444/market/products/{id}
//    @GetMapping("products/{id}")
//    public String showProductInfo(Model model, @PathVariable Long id) {
//        model.addAttribute("product", productService.findById(id));
//        return "product_info";
//    }
//
//    //    удаляет товар по ID
////    GET http://localhost:4444/market/products/delete/{id}
//    @GetMapping("products/delete/{id}")
//    public String deleteProductById(@PathVariable Long id) {
//        productService.deleteProductById(id);
//        return "redirect:/";
//    }
//
//    //    ищет товары выше минимальной цены
////    GET http://localhost:4444/market/products/find_by_min_price?min=
//    @GetMapping("products/find_by_min_price")
//    @ResponseBody
//    public List<Product> findByMinPrice(@RequestParam(name = "min") Integer min) {
//        return productService.findByMinPrice(min);
//    }
//
//
//    //    ищет товары ниже максимальной цены
////    GET http://localhost:4444/market/products/find_by_max_price?
//    @GetMapping("products/find_by_max_price")
//    @ResponseBody
//    public List<Product> findByMaxPrice(@RequestParam(name="max") Integer max) {
//        return productService.findByMaxPrice(max);
//    }
//
//    //    ищет товары с ценой в пределах мин и макс
////    GET http://localhost:4444/market/products/find_by_min_max_price?
//    @GetMapping("products/find_by_min_max_price")
//    @ResponseBody
//    public List<Product> findByMaxPrice(@RequestParam(name="min") Integer min, @RequestParam(name = "max") Integer max) {
//        return productService.findByMinAndMaxPrice(min, max);
//    }
}
