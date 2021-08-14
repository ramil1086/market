package ru.gb.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.market.models.Order;
import ru.gb.market.models.OrderItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private String address;
    private String phone;
    private BigDecimal price;
    private List<OrderItemDto> items;

    private final Function<OrderItem, OrderItemDto> mapOrderItemToOrderItemDto = oi -> {
        System.out.println(oi.getId() + " " + oi.getProduct().getTitle() + " " + oi.getPrice() + " " + oi.getQuantity());
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setProductId(oi.getId());
        System.out.println(orderItemDto.getProductId());
        orderItemDto.setProductTitle(oi.getProduct().getTitle());
        System.out.println(orderItemDto.getProductTitle());
        orderItemDto.setPrice(oi.getPrice());
        System.out.println(orderItemDto.getPrice());
        orderItemDto.setQuantity(oi.getQuantity());
        System.out.println(orderItemDto.getQuantity());
        orderItemDto.setPricePerProduct(oi.getPricePerProduct());
        System.out.println(orderItemDto.getPricePerProduct());
        return orderItemDto;
    };

    public OrderDto(Order order) {
        this.id = order.getId();
        this.address = order.getAddress();
        this.phone = order.getPhone();
        this.price = order.getPrice();
        this.items = order.getItems().stream().map(mapOrderItemToOrderItemDto).collect(Collectors.toList());
    }
}
