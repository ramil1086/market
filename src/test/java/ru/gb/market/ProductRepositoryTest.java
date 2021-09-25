package ru.gb.market;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import ru.gb.market.models.Product;
import ru.gb.market.repositories.ProductRepository;

import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void initDbTest() {
        List<Product> products = productRepository.findAll();
        Assertions.assertEquals(2, products.size());
    }
}
