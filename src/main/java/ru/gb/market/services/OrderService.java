package ru.gb.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.market.models.Order;
import ru.gb.market.models.Product;
import ru.gb.market.repositories.OrderRepository;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Order save(Order newOrder) {
        return orderRepository.save(newOrder);
    }
}
