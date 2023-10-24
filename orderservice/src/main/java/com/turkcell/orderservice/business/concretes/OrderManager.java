package com.turkcell.orderservice.business.concretes;

import com.turkcell.orderservice.business.abstracts.OrderService;
import com.turkcell.orderservice.dtos.response.CreatedGetOrderResponse;
import com.turkcell.orderservice.repositories.OrderRepository;

import java.util.List;

public class OrderManager implements OrderService {

    private final OrderRepository orderRepository;

    public OrderManager( OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<CreatedGetOrderResponse> getAll() {
        return orderRepository.getForListing();
    }
}
