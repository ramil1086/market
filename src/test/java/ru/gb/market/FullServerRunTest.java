package ru.gb.market;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import ru.gb.market.dto.CategoryDto;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FullServerRunTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void fullRestTest() {
        List<CategoryDto> categoryDtoList = testRestTemplate.getForObject("/api/v1/categories", List.class );
        Assertions.assertThat(categoryDtoList).isNotNull();
        Assertions.assertThat(categoryDtoList).isNotEmpty();

    }

}
