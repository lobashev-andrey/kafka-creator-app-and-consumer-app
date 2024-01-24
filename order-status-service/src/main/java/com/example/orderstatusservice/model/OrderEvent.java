package com.example.orderstatusservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderEvent {

    private String product;

    private Integer quantity;
}
