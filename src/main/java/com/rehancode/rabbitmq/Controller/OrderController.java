package com.rehancode.rabbitmq.Controller;

import com.rehancode.rabbitmq.Producer.Producer;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class OrderController {
    private final Producer producer;
    public OrderController(Producer producer) {
        this.producer = producer;
    }


    @PostMapping("order")
    public String order(){
        producer.sendMessage("Order Created Successfully");
        return "Order Created Successfully";
    }
}
