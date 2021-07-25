package ru.gb.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.market.models.Order;
import ru.gb.market.models.OrderItem;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private List<OrderItem> itemList;
    private BigDecimal price;

    public OrderDto(Order order, List<OrderItem> list) {
        this.id = order.getId();
        this.itemList = list;
        this.price = order.getPrice();
    }
}
