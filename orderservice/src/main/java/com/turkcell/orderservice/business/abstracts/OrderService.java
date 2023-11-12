package com.turkcell.orderservice.business.abstracts;


import com.turkcell.orderservice.entities.Order;

import java.util.List;

public interface OrderService {

   List<Order> getAll();

}
