package ru.gb.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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
    private final OrderService orderService;
    private final OrderItemService orderItemService;




    @GetMapping("/{uuid}")
    public Cart getCart(Principal principal, @PathVariable String uuid) {

        return cartService.getCurrentCart(getCurrentCartUuid(principal, uuid));
    }

    @GetMapping("/{uuid}/add/{productId}")
    public void add(Principal principal, @PathVariable String uuid, @PathVariable Long productId) {
       cartService.addToCart(getCurrentCartUuid(principal, uuid), productId);
    }

    @GetMapping("/{uuid}/quantity")
    public void changeQuantity(Principal principal, @PathVariable String uuid, @RequestParam(name = "a") int amount, @RequestParam(name = "p") Long productId) {
cartService.changeQuantity(getCurrentCartUuid(principal, uuid), productId, amount);
    }

    @DeleteMapping("/{uuid}/delete/{productId}")
    public void delete(Principal principal, @PathVariable String uuid, @PathVariable Long productId) {

        cartService.removeItemFromCart(getCurrentCartUuid(principal, uuid), productId);
    }

    @GetMapping("/{uuid}/clear")
    public void clearCart(Principal principal, @PathVariable String uuid) {
cartService.clearCart(getCurrentCartUuid(principal, uuid));

    }

    @GetMapping("/{uuid}/merge")
    public void mergeCart(Principal principal, @PathVariable String uuid) {
cartService.merge(
        getCurrentCartUuid(principal, null),
        getCurrentCartUuid(null, uuid)
);
    }

    private String getCurrentCartUuid(Principal principal, String uuid) {
        if (principal != null) {
            return cartService.getCartFromSuffix(principal.getName());
        }
        return cartService.getCartFromSuffix(uuid);
    }

    @GetMapping("/generate")
    public StringResponse getCart() {
        return new StringResponse(cartService.generateCartUUid());
    }

}
