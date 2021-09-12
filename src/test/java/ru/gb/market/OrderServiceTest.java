package ru.gb.market;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.gb.market.models.Category;
import ru.gb.market.models.Product;
import ru.gb.market.services.OrderService;
import ru.gb.market.services.ProductService;
import ru.gb.market.utils.Cart;

import java.math.BigDecimal;
import java.util.Optional;

@SpringBootTest
public class OrderServiceTest {
    @Autowired
    private Cart cart;

    @MockBean
    private ProductService productService;

    @BeforeEach
    public void initCart() {
        cart.clear();
    }

    @Test
    public void saveProductTest() {
        Product product = new Product();
        product.setId(1L);
        product.setPrice(BigDecimal.valueOf(999));
        product.setTitle("TestProduct");
        Category category = new Category();
        category.setTitle("Food");
        product.setCategory(category);
        productService.save(product);

        cart.add(product);
        cart.add(product);
        cart.add(product);
        Assertions.assertEquals(3, cart.getItems().size());


    }


}
