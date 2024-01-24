package com.example.orderstatusservice.listener;

import com.example.orderservice.model.OrderEvent;
import com.example.orderservice.model.StatusEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderEventListener {

    private final KafkaTemplate<String, StatusEvent> template;

    @Value("${app.kafka.orderStatusTopic}")
    private String orderStatusTopic;

    @KafkaListener( topics = "${app.kafka.orderTopic}",
                    containerFactory = "containerFactory",
                    groupId = "${app.kafka.orderGroupId}")
    public void listen(@Payload OrderEvent event,
                       @Header(value = KafkaHeaders.RECEIVED_KEY, required = false) UUID key,
                       @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                       @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
                       @Header(KafkaHeaders.RECEIVED_TIMESTAMP) Long timestamp){

        log.info("***** Receiving OrderEvent from OrderServiceApplication *****");
        log.info("MIDPOINT Received message: {}" , event);
        log.info("MIDPOINT Key: {}; Partition: {}; Topic: {}, Timestamp: {}", key, partition, topic, timestamp);

        template.send(orderStatusTopic, new StatusEvent("CREATED", LocalDateTime.now()));

    }
}