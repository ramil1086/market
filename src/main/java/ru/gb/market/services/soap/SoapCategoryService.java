package ru.gb.market.services.soap;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.market.models.Category;
import ru.gb.market.repositories.soap.SoapCategoryRepository;
import ru.gb.market.soap.categories.Categorysoap;

import javax.xml.datatype.DatatypeFactory;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class SoapCategoryService {
    private final SoapCategoryRepository soapCategoryRepository;

    public static final Function<Category, Categorysoap> functionEntityToSoap = c -> {
        Categorysoap categorysoap = new Categorysoap();
        categorysoap.setId(c.getId());
        categorysoap.setTitle(c.getTitle());
        c.getProducts().stream().map(SoapProductService.functionEntityToSoap).forEach(p-> categorysoap.getProducts().add(p));
        return categorysoap;
    };

    public Categorysoap getById(Long id) {
        return soapCategoryRepository.findById(id).map(functionEntityToSoap).get();
    }
}
