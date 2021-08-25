package ru.gb.market.repositories.soap;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.gb.market.models.Category;

import java.util.Optional;

@Repository
public interface SoapCategoryRepository extends JpaRepository<Category, Long> {
    @Query("select c from Category c join fetch c.products where c.id = :id")
    Optional<Category> findById(Long id);
}
