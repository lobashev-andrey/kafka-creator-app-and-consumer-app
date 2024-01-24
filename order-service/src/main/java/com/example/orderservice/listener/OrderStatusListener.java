package com.example.orderservice.listener;

import com.example.orderservice.model.StatusEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderStatusListener {

    @KafkaListener( topics = "${app.kafka.orderStatusTopic}",
                    groupId = "${app.kafka.orderGroupId}",
                    containerFactory = "containerFactory")
    public void listen( @Payload StatusEvent event,
                        @Header(value = KafkaHeaders.RECEIVED_KEY, required = false) UUID key,
                        @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
                        @Header(KafkaHeaders.RECEIVED_TIMESTAMP) Long timestamp){

        log.info("***** Receiving StatusEvent from OrderStatusServiceApplication *****");
        log.info("Received message: {}" , event);
        log.info("Key: {}; Partition: {}; Topic: {}, Timestamp: {}", key, partition, topic, timestamp);


    }
}
