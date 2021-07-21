package ru.gb.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.market.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
