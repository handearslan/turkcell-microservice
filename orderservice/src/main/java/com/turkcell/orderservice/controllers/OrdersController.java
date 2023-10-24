package com.turkcell.orderservice.controllers;


import com.turkcell.orderservice.business.abstracts.OrderService;
import com.turkcell.orderservice.dtos.request.CreateOrderRequest;
import com.turkcell.orderservice.dtos.response.CreatedGetOrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RequestMapping("api/v1/orders")
@RestController
@RequiredArgsConstructor
public class OrdersController {

    private final OrderService orderService;

    private final WebClient.Builder webClientBuilder;

    @GetMapping()
    public List<CreatedGetOrderResponse> getOrders() {
        List<CreatedGetOrderResponse> ordersInDb = orderService.getAll();
        return ordersInDb;
    }

    @PostMapping
    public ResponseEntity<Boolean> submitOrder(@RequestBody CreateOrderRequest request)
    {
        // Web istekleri default async
        // sync
        Boolean hasStock = webClientBuilder.build()
                .get()
                .uri("http://localhost:8083/api/v1/products/check-stock",
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
}
