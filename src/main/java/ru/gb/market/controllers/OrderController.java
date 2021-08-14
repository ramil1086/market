package ru.gb.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.market.dto.OrderDto;
import ru.gb.market.exceptions.InvalidParamsException;
import ru.gb.market.exceptions.MarketError;
import ru.gb.market.exceptions.ResourceNotFoundException;
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
    public void createOrder(Principal principal, @RequestParam String address, @RequestParam String phone) {
        StringBuilder errorMsg = new StringBuilder();
        if (address.isBlank()) errorMsg.append(" address ");
        if (phone.isBlank()) errorMsg.append(" phone ");
        if (errorMsg.length() > 0) throw new InvalidParamsException("Please, enter: " + errorMsg.toString());
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Unable to create order: user not found"));
        System.out.println(user.getEmail());
//        orderService.createOrder(user.getEmail());
        orderService.createOrder(user, address, phone);
    }

    @GetMapping
    public List<OrderDto> getAllOrders(Principal principal) {
        User user = userService.findByUsername(principal.getName()).get();
        return orderService.findAll().stream().filter(u -> u.getEmail().equals(user.getEmail())).map(OrderDto::new).collect(Collectors.toList());
//        return orderService.findAll().stream().filter(u->u.getId().equals(user.getId())).map(OrderDto::new).collect(Collectors.toList());
    }
}
