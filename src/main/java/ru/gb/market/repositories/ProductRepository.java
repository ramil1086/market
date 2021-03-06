package ru.gb.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.gb.market.models.Product;

import java.util.*;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    List<Product> findAllByPriceGreaterThanEqual(int minPrice);
    List<Product> findAllByPriceLessThanEqual(int maxPrice);
    List<Product> findAllByPriceBetween(int minPrice, int maxPrice);

//    List<Product> findAllByIdLessThanEqual(Long maxId);
//    List<Product> findAllByIdBetweenAndPriceGreaterThan(Long minId, Long maxId, int minPrice);
//    @Query ("select p from Product p where p.id > :min")
//    List<Product> myCustomFinder(Long min);
//    Product findProductById(Long id);

}
