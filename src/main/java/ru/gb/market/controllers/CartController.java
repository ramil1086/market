package ru.gb.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.market.dto.OrderDto;
import ru.gb.market.dto.OrderItemDto;
import ru.gb.market.dto.StringResponse;
import ru.gb.market.exceptions.ResourceNotFoundException;
import ru.gb.market.models.Order;
import ru.gb.market.models.OrderItem;
import ru.gb.market.models.Product;
import ru.gb.market.services.CartService;
import ru.gb.market.services.OrderItemService;
import ru.gb.market.services.OrderService;
import ru.gb.market.services.ProductService;
import ru.gb.market.utils.Cart;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final ProductService productService;
    private final OrderService orderService;
    private final OrderItemService orderItemService;

    @GetMapping("/{uuid}")
    public Cart getCart(Principal principal, @PathVariable String uuid) {

        return cartService.getCurrentCart(getCurrentCartSuffix(principal, uuid));
    }

    @GetMapping("/{uuid}/add/{productId}")
    public void add(Principal principal, @PathVariable String uuid, @PathVariable Long productId) {
        Cart cart = cartService.getCurrentCart(getCurrentCartSuffix(principal, uuid));
        if (cart.add(productId)) {
            cartService.updateCart(cart, getCurrentCartSuffix(principal, uuid));
            return;
        }
        Product p = productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Unable add product to cart. Product not found, id: " + productId));
        cart.add(p);
        cartService.updateCart(cart, getCurrentCartSuffix(principal, uuid));
    }

    @GetMapping("/{uuid}/quantity")
    public void changeQuantity(Principal principal, @PathVariable String uuid, @RequestParam(name = "a") int amount, @RequestParam(name = "p") Long productId) {

        Cart cart = cartService.getCurrentCart(getCurrentCartSuffix(principal, uuid));
        cart.changeQuantity(amount, productId);
        cartService.updateCart(cart, getCurrentCartSuffix(principal, uuid));
    }

    @DeleteMapping("/{uuid}/delete/{productId}")
    public void delete(Principal principal, @PathVariable String uuid, @PathVariable Long productId) {
        Cart cart = cartService.getCurrentCart(getCurrentCartSuffix(principal, uuid));
        cart.delete(productId);
        cartService.updateCart(cart, getCurrentCartSuffix(principal, uuid));
    }

    @GetMapping("/{uuid}/clear")
    public void clearCart(Principal principal, @PathVariable String uuid) {

        Cart cart = cartService.getCurrentCart(getCurrentCartSuffix(principal, uuid));
        cart.clear();
        cartService.updateCart(cart, getCurrentCartSuffix(principal, uuid));
    }

    @GetMapping("/{uuid}/merge")
    public void mergeCart(Principal principal, @PathVariable String uuid) {

        Cart guestCart = cartService.getCurrentCart(uuid);
        Cart userCart = cartService.getCurrentCart(principal.getName());
        userCart.merge(guestCart);
        cartService.updateCart(userCart, principal.getName());
        cartService.deleteCart(uuid);
    }

    private String getCurrentCartSuffix(Principal principal, String uuid) {
        if (principal != null) {
            return principal.getName();
        }
        return uuid;
    }

    @GetMapping("/generate")
    public StringResponse getCart() {
        return new StringResponse(cartService.generateCart());
    }

}
