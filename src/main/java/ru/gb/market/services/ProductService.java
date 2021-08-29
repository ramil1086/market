package ru.gb.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.gb.market.models.Product;
import ru.gb.market.repositories.ProductRepository;
import ru.gb.market.soap.products.Productsoap;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public static final Function<Product, Productsoap> functionEntityToSoap = p -> {
        Productsoap productsoap = new Productsoap();
        productsoap.setId(p.getId());
        productsoap.setTitle(p.getTitle());
        productsoap.setPrice(p.getPrice());
        productsoap.setCategory(p.getCategory().getTitle());
        return productsoap;
    };

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    public Page<Product> findPage(int pageIndex, int pageSize, Specification<Product> spec) {
        return productRepository.findAll(spec, PageRequest.of(pageIndex, pageSize));
    }

    public Product save(Product newProduct) {
        return productRepository.save(newProduct);
    }

    public List<Productsoap> getAllProducts() {
        return productRepository.findAll().stream().map(functionEntityToSoap).collect(Collectors.toList());
    }

    public Productsoap findSoapById(Long id) {
        return productRepository.findById(id).map(functionEntityToSoap).get();
    }
}

