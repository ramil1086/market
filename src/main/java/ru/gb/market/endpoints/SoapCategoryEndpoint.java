package ru.gb.market.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.gb.market.services.CategoryService;
import ru.gb.market.soap.categories.GetCategoryByIdRequest;
import ru.gb.market.soap.categories.GetCategoryByIdResponse;

@Endpoint
@RequiredArgsConstructor
public class SoapCategoryEndpoint {
    private static final String NAMESPACE_URI = "http://www.marketapp.ru/spring/ws/categories";
    private final CategoryService categoryService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCategoryByIdRequest")
    @ResponsePayload
    public GetCategoryByIdResponse getCategoryByIdResponse(@RequestPayload GetCategoryByIdRequest request) {
        GetCategoryByIdResponse response = new GetCategoryByIdResponse();
        response.setCategorysoap(categoryService.getById(request.getId()));
        return response;
    }

/*
Пример запроса: POST http://localhost:4444/market/ws


        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
          xmlns:f="http://www.marketapp.ru/spring/ws/categories">
            <soapenv:Header/>
            <soapenv:Body>
                <f:getCategoryByIdRequest>
                    <f:id>1</f:id>
                </f:getCategoryByIdRequest>
            </soapenv:Body>
        </soapenv:Envelope>
     */
}
