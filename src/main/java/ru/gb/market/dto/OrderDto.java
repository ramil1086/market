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

//    private final Function<OrderItem, OrderItemDto> mapOrderItemToOrderItemDto = oi -> {
//        System.out.println(oi.getId() + " " + oi.getProduct().getTitle() + " " + oi.getPrice() + " " + oi.getQuantity());
//        OrderItemDto orderItemDto = new OrderItemDto();
//        orderItemDto.setProductId(oi.getId());
//        orderItemDto.setProductTitle(oi.getProduct().getTitle());
//        orderItemDto.setPrice(oi.getPrice());
//        orderItemDto.setQuantity(oi.getQuantity());
//        orderItemDto.setPricePerProduct(oi.getPricePerProduct());
//        return orderItemDto;
//    };

    public OrderDto(Order order) {
        this.id = order.getId();
        this.address = order.getAddress();
        this.phone = order.getPhone();
        this.price = order.getPrice();
//        this.items = order.getItems().stream().map(mapOrderItemToOrderItemDto).collect(Collectors.toList());
    this.items = order.getItems().stream().map(OrderItemDto::new).collect(Collectors.toList());
    }
}
