package ru.gb.market.utils;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.MultiValueMap;
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

    public CustomSpecification(MultiValueMap<String, String> params) {
        productSpecification = Specification.where(null);
        if (params.containsKey("min_price") && !params.getFirst("min_price").isBlank()) productSpecification = productSpecification.and(ProductSpecifications.priceGreaterOrEqualThan(BigDecimal.valueOf(Double.parseDouble(params.getFirst("min_price")))));
        if (params.containsKey("max_price") && !params.getFirst("max_price").isBlank()) productSpecification = productSpecification.and(ProductSpecifications.priceLessOrEqualThan(BigDecimal.valueOf(Double.parseDouble(params.getFirst("max_price")))));
        if (params.containsKey("title") && !params.getFirst("title").isBlank()) productSpecification = productSpecification.and(ProductSpecifications.titleLike(params.getFirst("title")));



//        if (minPrice != null) productSpecification = productSpecification.and(ProductSpecifications.priceGreaterOrEqualThan(minPrice));
//        if (maxPrice != null) productSpecification = productSpecification.and(ProductSpecifications.priceLessOrEqualThan(maxPrice));
//        if (title != null) productSpecification = productSpecification.and(ProductSpecifications.titleLike(title));
    }


}
