package com.example.orderservice.model;

import lombok.Data;

@Data
public class Order {
    private String product;
    private Integer quantity;

    public OrderEvent toOrderEvent(){
        return new OrderEvent(this.getProduct(), this.getQuantity());
    }
}
