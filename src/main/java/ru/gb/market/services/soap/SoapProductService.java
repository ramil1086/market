package ru.gb.market.services.soap;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.market.models.Product;
import ru.gb.market.repositories.soap.SoapProductRepository;
import ru.gb.market.soap.products.Productsoap;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SoapProductService {
    private final SoapProductRepository soapProductRepository;

    public static final Function<Product, Productsoap> functionEntityToSoap = p -> {
        Productsoap productsoap = new Productsoap();
        productsoap.setId(p.getId());
        productsoap.setTitle(p.getTitle());
        productsoap.setPrice(p.getPrice());
        productsoap.setCategory(p.getCategory().getTitle());
        return productsoap;
    };

    public List<Productsoap> getAllProducts() {
        return soapProductRepository.findAll().stream().map(functionEntityToSoap).collect(Collectors.toList());
    }

public Productsoap getById(Long id) {
        return soapProductRepository.findById(id).map(functionEntityToSoap).get();
}
}
