package ru.gb.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.market.dto.OrderDto;
import ru.gb.market.dto.OrderItemDto;
import ru.gb.market.exceptions.ResourceNotFoundException;
import ru.gb.market.models.Order;
import ru.gb.market.models.OrderItem;
import ru.gb.market.models.Product;
import ru.gb.market.models.User;
import ru.gb.market.repositories.OrderRepository;
import ru.gb.market.utils.Cart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final CartService cartService;

    @Transactional
    public void createOrder(User user, String address, String phone) {
        Cart cart = cartService.getCurrentCart(cartService.getCartFromSuffix(user.getUsername()));
        Order order = new Order();
        order.setUser(user);
        order.setAddress(address);
        order.setPhone(phone);
        order.setEmail(user.getEmail());
        order.setPrice(cart.getPrice());
        order.setItems(new ArrayList<>());
        for (OrderItemDto o : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setQuantity(o.getQuantity());
            Product product = productService.findById(o.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
            orderItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(o.getQuantity())));
            orderItem.setPricePerProduct(product.getPrice());
            orderItem.setProduct(product);
            order.getItems().add(orderItem);
        }
        orderRepository.save(order);
        cart.clear();
        cartService.updateCart(cartService.getCartFromSuffix(user.getUsername()),cart);
    }
//
//    public void createOrder(String email) {
//        Order order = new Order();
//        order.setEmail(email);
//        order.setPrice(cart.getPrice());
//        order.setItems(new ArrayList<>());
//        for (OrderItemDto o : cart.getItems()) {
//            OrderItem orderItem = new OrderItem();
//            orderItem.setOrder(order);
//            orderItem.setQuantity(o.getQuantity());
//            Product product = productService.findById(o.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
//            orderItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(o.getQuantity())));
//            orderItem.setPricePerProduct(product.getPrice());
//            orderItem.setProduct(product);
//            order.getItems().add(orderItem);
//        }
//        orderRepository.save(order);
//        cart.clear();
//    }

//    public List<Order> findAll() {
//        return orderRepository.findAll();
//    }

    @Transactional
    public List<OrderDto> findAllDtosByUser(User user) {
        return orderRepository.findAllByUser(user).stream().map(OrderDto::new).collect(Collectors.toList());
    }
}
