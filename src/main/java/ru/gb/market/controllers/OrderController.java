package ru.gb.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.market.dto.OrderDto;
import ru.gb.market.models.Order;
import ru.gb.market.models.User;
import ru.gb.market.services.OrderService;
import ru.gb.market.services.UserService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @PostMapping
    public void createOrder(Principal principal) {
        User user = userService.findByUsername(principal.getName()).get();
        System.out.println(user.getEmail());
        orderService.createOrder(user.getEmail());
    }

    @GetMapping
    public List<OrderDto> getAllOrders(Principal principal) {
        User user = userService.findByUsername(principal.getName()).get();
        return orderService.findAll().stream().filter(u->u.getEmail().equals(user.getEmail())).map(OrderDto::new).collect(Collectors.toList());
    }
}
