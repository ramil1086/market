package ru.gb.market;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import ru.gb.market.models.Category;
import ru.gb.market.models.Product;
import ru.gb.market.models.Role;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.math.BigDecimal;

@org.springframework.boot.test.autoconfigure.json.JsonTest
public class ProductRoleJsonTest {
    @Autowired
    private JacksonTester<Product> jacksonTesterProduct;
    @Autowired
    JacksonTester<Role> jacksonTesterRole;

    @Test
    public void jsonSerializerTest() throws IOException {
        Product product = new Product();
        product.setId(1L);
        product.setTitle("TestJsonProduct");
        product.setPrice(BigDecimal.valueOf(999));
        Category category = new Category();
        category.setTitle("TestCategory");
        product.setCategory(category);

        assertThat(jacksonTesterProduct.write(product))
                .hasJsonPathValue("$.id")
                .extractingJsonPathStringValue("$.title").isEqualTo("TestJsonProduct");
    }
        @Test
        public void jsonDeserializerTest () throws IOException {
            String content = "{\"id\": 3,\"name\":\"ADMIN\"}";
            Role role = new Role();
            role.setId(3L);
            role.setName("ADMIN");

            assertThat(jacksonTesterRole.parse(content)).isEqualTo(role);
            assertThat(jacksonTesterRole.parseObject(content).getName()).isEqualTo("ADMIN");
        }
    }

