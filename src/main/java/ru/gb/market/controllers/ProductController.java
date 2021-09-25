package ru.gb.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.gb.market.dto.ProductDto;
import ru.gb.market.models.Category;
import ru.gb.market.models.Product;
import ru.gb.market.repositories.specifications.ProductSpecifications;
import ru.gb.market.services.CategoryService;
import ru.gb.market.services.ProductService;
import ru.gb.market.exceptions.ResourceNotFoundException;
import ru.gb.market.utils.CustomSpecification;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
//    private static final Function<Product, ProductDto> mapProductToProductDto = p -> new ProductDto(p.getId(), p.getTitle(), p.getPrice(), p.getCategory().getTitle());

    //    GET http://localhost:4444/market/api/v1/products/{id}
    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        Product p = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found, id: " + id));
        return new ProductDto(p);
//        if (p.isPresent()) {
//            return new ResponseEntity<>(new ProductDto(p.get()), HttpStatus.OK);
//        }
//        throw new ResourceNotFoundException("Product not found, id: " + id);
//        return new ResponseEntity<>(new MarketError(HttpStatus.NOT_FOUND.value(), "Product not found, id: " + id),HttpStatus.NOT_FOUND);
//        return new ProductDto(productService.findById(id));

//        return mapProductToProductDto.apply(productService.findById(id));
    }


    //    GET http://localhost:4444/market/api/v1/products
    @GetMapping
    public Page<ProductDto> findAll(
            @RequestParam(name = "p", defaultValue = "1") int pageIndex,
            @RequestParam MultiValueMap<String, String>  params

//            @RequestParam(name = "min_price", required = false) BigDecimal minPrice,
//            @RequestParam(name = "max_price", required = false) BigDecimal maxPrice,
//            @RequestParam(name = "title", required = false) String title
    ) {
        Specification<Product> productSpec = new CustomSpecification<Product>(params).getProductSpecification();
        return productService.findPage(pageIndex - 1, 10, productSpec).map(ProductDto::new);

        //        Specification<Product> spec = Specification.where(null);
//        if (minPrice != null) spec = spec.and(ProductSpecifications.priceGreaterOrEqualThan(minPrice));
//        if (maxPrice != null) spec = spec.and(ProductSpecifications.priceLessOrEqualThan(maxPrice));
//        if (title != null) spec = spec.and(ProductSpecifications.titleLike(title));
//        return productService.findPage(pageIndex - 1, 10, spec).map(ProductDto::new);
//        return productService.findPage(pageIndex-1,10).map(mapProductToProductDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
    }


    @PostMapping
    public ProductDto createNewProduct(@RequestBody ProductDto newProductDto) {
        Product product = new Product();
        product.setTitle(newProductDto.getTitle());
        product.setPrice(newProductDto.getPrice());
        Category category = categoryService.findByTitle(newProductDto.getCategoryTitle());
        product.setCategory(category);
        return new ProductDto(productService.save(product));
    }


}
