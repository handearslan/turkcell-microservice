package com.turkcell.orderservice.business.abstracts;

import com.turkcell.orderservice.dtos.response.CreatedGetOrderResponse;

import java.util.List;

public interface OrderService {

    List<CreatedGetOrderResponse> getAll();


}
