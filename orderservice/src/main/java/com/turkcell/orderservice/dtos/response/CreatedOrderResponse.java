package com.turkcell.orderservice.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatedOrderResponse {
    private String inventoryCode;
    private String hasStock;
    private Integer stockAmount;

}
