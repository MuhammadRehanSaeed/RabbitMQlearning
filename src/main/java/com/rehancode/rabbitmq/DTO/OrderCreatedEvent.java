package com.rehancode.rabbitmq.DTO;


import lombok.Data;

@Data
public class OrderCreatedEvent {
    private String messageId;
    private Long orderId;

    private String customerName;

    private String email;

    private double amount;

    private int retryCount=0;
}
