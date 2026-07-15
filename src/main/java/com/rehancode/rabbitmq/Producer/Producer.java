package com.rehancode.rabbitmq.Producer;

import com.rehancode.rabbitmq.Configuration.RabbitMqConfig;
import com.rehancode.rabbitmq.DTO.OrderCreatedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;


@Service
public class Producer {
    private final RabbitTemplate rabbitTemplate;
    public Producer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    //now we are sending just message intead we should send dto but rabbitmq doesn't
    //direclty understand java objects so we need to converrt them to JSON using
    //message converter specifically Jackson2JsonMessageConverter

    public void sendMessage(String message) {
        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent();
        orderCreatedEvent.setMessageId("2");
        orderCreatedEvent.setOrderId(1L);
        orderCreatedEvent.setAmount(2500.0);
        orderCreatedEvent.setEmail("rehan@gmail.com");
        orderCreatedEvent.setCustomerName("rehan");
         rabbitTemplate.convertAndSend(RabbitMqConfig.orderexchange,RabbitMqConfig.orderroutingKey,orderCreatedEvent);
    }
}
