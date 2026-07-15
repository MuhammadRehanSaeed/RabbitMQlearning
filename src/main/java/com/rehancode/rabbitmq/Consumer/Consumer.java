package com.rehancode.rabbitmq.Consumer;


import com.rehancode.rabbitmq.Configuration.RabbitMqConfig;
import com.rehancode.rabbitmq.DTO.OrderCreatedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {


    @RabbitListener(queues = RabbitMqConfig.orderqueue)
    public void receive(OrderCreatedEvent  orderCreatedEvent)
    {

        System.out.println("Received OrderCreatedEvent" + orderCreatedEvent.getCustomerName());


    }
}
