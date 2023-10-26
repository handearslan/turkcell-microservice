package com.turkcell.orderservice.controllers;

import com.turkcell.orderservice.business.abstracts.OrderService;
import com.turkcell.orderservice.dtos.request.CreateOrderRequest;
import com.turkcell.orderservice.dtos.response.CreatedOrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("api/v1/orders")
@RestController
@RequiredArgsConstructor
public class OrdersController {

    private final OrderService orderService;

    private final WebClient.Builder webClientBuilder;

    @PostMapping
    public ResponseEntity<Boolean> submitOrder(@RequestBody CreateOrderRequest request)
    {
        // Web istekleri default async
        // sync
        Boolean hasStock = webClientBuilder.build()
                .get()
                .uri("http://product-service/api/v1/products/check-stock",
                        (uriBuilder) -> uriBuilder
                                .queryParam("invCode",request.getInventoryCode())
                                .queryParam("requiredStock",request.getAmount())
                                .build())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
        // senkron
        return new ResponseEntity<>(hasStock, hasStock ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @PostMapping("add")
    public List<CreatedOrderResponse> submitOrderAndStock(@RequestBody List<CreateOrderRequest> request)
    {
        List<CreatedOrderResponse> returnList = new ArrayList<>();

        for (CreateOrderRequest item : request) {
            Boolean hasStock = webClientBuilder.build()
                    .get()
                    .uri("http://product-service/api/v1/products/check-stock",
                            (uriBuilder) -> uriBuilder
                                    .queryParam("invCode",item.getInventoryCode())
                                    .queryParam("requiredStock",item.getAmount())
                                    .build())
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();

            Integer aa = webClientBuilder.build()
                    .get()
                    .uri("http://product-service/api/v1/products/get-stock",
                            (uriBuilder) -> uriBuilder
                                    .queryParam("invCode",item.getInventoryCode())
                                    .build())
                    .retrieve()
                    .bodyToMono(Integer.class)
                    .block();

            CreatedOrderResponse od = new CreatedOrderResponse();
            od.setStockAmount(aa);
            od.setHasStock(hasStock.toString());
            od.setInventoryCode(item.getInventoryCode());
            returnList.add(od);

        }
        // Web istekleri default async
        // sync

        // senkron
        //return new ResponseEntity<>(returnList, returnList ? HttpStatus.OK : HttpStatus.BAD_REQUEST);

        return returnList;
    }
}
