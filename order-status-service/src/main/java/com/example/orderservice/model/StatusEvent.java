package com.example.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class StatusEvent {

    private String status;

    private LocalDateTime date;
}