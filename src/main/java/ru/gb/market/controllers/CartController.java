package ru.gb.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.market.dto.OrderDto;
import ru.gb.market.dto.OrderItemDto;
import ru.gb.market.exceptions.ResourceNotFoundException;
import ru.gb.market.models.Order;
import ru.gb.market.models.OrderItem;
import ru.gb.market.models.Product;
import ru.gb.market.services.OrderItemService;
import ru.gb.market.services.OrderService;
import ru.gb.market.services.ProductService;
import ru.gb.market.utils.Cart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final Cart cart;
    private final ProductService productService;
    private final OrderService orderService;
    private final OrderItemService orderItemService;

    @GetMapping
    public Cart getCart() {
        return cart;
    }

    @GetMapping("/add/{productId}")
    public void add(@PathVariable Long productId) {
        if (cart.add(productId)) {
            return;
        }
        Product p = productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Unable add product to cart. Product not found, id: " + productId));
        cart.add(p);
    }

    @GetMapping("/quantity")
    public void changeQuantity(@RequestParam (name ="a") int amount, @RequestParam (name = "p") Long productId) {
        cart.changeQuantity(amount,productId);
    }

    @DeleteMapping("/delete/{productId}")
    public void delete(@PathVariable Long productId) {
        cart.delete(productId);
    }

    @GetMapping("/clear")
    public void clearCart() {
        cart.clear();
    }


}
