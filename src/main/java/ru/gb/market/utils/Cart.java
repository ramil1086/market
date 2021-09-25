package ru.gb.market.utils;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.market.dto.OrderItemDto;
import ru.gb.market.dto.ProductDto;
import ru.gb.market.models.Product;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//@Component
@Data
//@NoArgsConstructor
public class Cart {
    private List<OrderItemDto> items;
    private BigDecimal price;

//    @PostConstruct
    public Cart() {
        this.items = new ArrayList<>();
        this.price = BigDecimal.ZERO;
    }

    public void clear() {
        items.clear();
        recalculate();
    }

    public boolean add(Long productId) {
        for (OrderItemDto oid : items) {
            if (oid.getProductId().equals(productId)) {
                oid.changeQuantity(1);
                recalculate();
                return true;
            }
        }
        return false;
    }

    public void add(Product product) {
        items.add(new OrderItemDto(product));
        recalculate();
    }

    public void recalculate() {
        price = BigDecimal.ZERO;
        for (OrderItemDto oid : items) {
            price = price.add(oid.getPrice());
        }
    }

    public void changeQuantity(int amount, Long productId) {
        for (OrderItemDto oid : items) {
            if (oid.getProductId().equals(productId)) {
                oid.changeQuantity(amount);
                recalculate();
                if (oid.getQuantity() == 0) {
                    items.remove(oid);
                    recalculate();
                }
                return;
            }
        }
    }

    public void delete(Long productId) {
//        for (OrderItemDto oid : items) {
//            if (oid.getProductId().equals(productId)) {
//                items.remove(oid);
//                recalculate();
//                return;
//            }
//        }
        items.removeIf(oi -> oi.getProductId().equals(productId));
        recalculate();
    }

    public void merge(Cart another) {
        for (OrderItemDto anotheItem : another.items) {
            boolean merged = false;
            for (OrderItemDto myItem : items){
                if (myItem.getProductId().equals(anotheItem.getProductId())) {
                    myItem.changeQuantity(anotheItem.getQuantity());
                    merged = true;
                    break;
                }
            }
            if (!merged) {
                items.add(anotheItem);
            }

        }
        recalculate();
        another.clear();
    }

}
