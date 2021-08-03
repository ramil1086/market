package ru.gb.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.market.dto.OrderItemDto;
import ru.gb.market.exceptions.ResourceNotFoundException;
import ru.gb.market.models.Order;
import ru.gb.market.models.OrderItem;
import ru.gb.market.models.Product;
import ru.gb.market.repositories.OrderRepository;
import ru.gb.market.utils.Cart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final Cart cart;

    @Transactional
    public void createOrder(String email) {
        Order order = new Order();
        order.setEmail(email);
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
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

}
