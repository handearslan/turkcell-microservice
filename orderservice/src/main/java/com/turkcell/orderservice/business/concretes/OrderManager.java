package com.turkcell.orderservice.business.concretes;

import com.turkcell.orderservice.business.abstracts.OrderService;
import com.turkcell.orderservice.entities.Order;
import com.turkcell.orderservice.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderManager implements OrderService  {

    private final OrderRepository orderRepository;

   @Override
    public List<Order> getAll() {
        return orderRepository.getForListing();
    }
}
