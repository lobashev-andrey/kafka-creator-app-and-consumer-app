package com.example.orderservice.controller;


import com.example.orderservice.model.Order;
import com.example.orderservice.model.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/someaddress")
@RequiredArgsConstructor
public class OrderController {

    @Value("${app.kafka.orderTopic}")
    private String topicName;

    private final KafkaTemplate<String, OrderEvent> template;

    @PostMapping
    public void add(@RequestBody Order order){
        template.send(topicName, order.toOrderEvent());
    }
}
