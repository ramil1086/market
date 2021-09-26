package ru.gb.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.market.models.Category;
import ru.gb.market.repositories.CategoryRepository;
import ru.gb.market.soap.categories.Categorysoap;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public static final Function<Category, Categorysoap> functionEntityToSoap = c -> {
        Categorysoap categorysoap = new Categorysoap();
        categorysoap.setId(c.getId());
        categorysoap.setTitle(c.getTitle());
        c.getProducts().stream().map(ProductService.functionEntityToSoap).forEach(p -> categorysoap.getProducts().add(p));
        return categorysoap;
    };

    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category findByTitle(String categoryTitle) {
        return categoryRepository.findByTitle(categoryTitle);
    }

    public Categorysoap getById(Long id) {
        return categoryRepository.findById(id).map(functionEntityToSoap).get();
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
