package ru.gb.market.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.gb.market.services.soap.SoapProductService;
import ru.gb.market.soap.products.GetAllProductsRequest;
import ru.gb.market.soap.products.GetAllProductsResponse;
import ru.gb.market.soap.products.GetProductByIdRequest;
import ru.gb.market.soap.products.GetProductByIdResponse;

@Endpoint
@RequiredArgsConstructor
public class SoapProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.marketapp.ru/spring/ws/products";
    private final SoapProductService soapProductService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductByIdRequest")
    @ResponsePayload
    public GetProductByIdResponse getProductByIdResponse(@RequestPayload GetProductByIdRequest request) {
        GetProductByIdResponse response = new GetProductByIdResponse();
        response.setProductsoap(soapProductService.getById(request.getId()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllProductsResponse(@RequestPayload GetAllProductsRequest request) {
        GetAllProductsResponse response = new GetAllProductsResponse();
        soapProductService.getAllProducts().forEach(response.getProducts()::add);
        return response;
    }

     /*
        Пример запроса: POST http://localhost:4444/market/ws

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:f="http://www.marketapp.ru/spring/ws/products">
            <soapenv:Header/>
            <soapenv:Body>
                <f:getAllProductsRequest/>
            </soapenv:Body>
        </soapenv:Envelope>


            <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
          xmlns:f="http://www.marketapp.ru/spring/ws/products">
            <soapenv:Header/>
            <soapenv:Body>
                <f:getProductByIdRequest>
                    <f:id>7</f:id>
                </f:getProductByIdRequest>
            </soapenv:Body>
        </soapenv:Envelope>
     */
}
