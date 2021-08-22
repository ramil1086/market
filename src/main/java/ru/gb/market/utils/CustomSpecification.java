package ru.gb.market.utils;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import ru.gb.market.models.Product;
import ru.gb.market.repositories.specifications.ProductSpecifications;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CustomSpecification<T>  {
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private String title;
    private Specification<Product> productSpecification;

    public CustomSpecification(BigDecimal minPrice, BigDecimal maxPrice, String title) {
        productSpecification = Specification.where(null);
        if (minPrice != null) productSpecification = productSpecification.and(ProductSpecifications.priceGreaterOrEqualThan(minPrice));
        if (maxPrice != null) productSpecification = productSpecification.and(ProductSpecifications.priceLessOrEqualThan(maxPrice));
        if (title != null) productSpecification = productSpecification.and(ProductSpecifications.titleLike(title));
    }


}
