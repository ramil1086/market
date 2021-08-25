package ru.gb.market.repositories.soap;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.gb.market.models.Product;

import java.util.Optional;

@Repository
public interface SoapProductRepository extends JpaRepository<Product, Long> {
//    @Query("select p from Product p where p.id = :id")
    Optional<Product> findById(Long id);
}
