package com.rehancode.rabbitmq.Configuration;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.management.Query;


@Configuration
public class RabbitMqConfig {
    public static final  String orderexchange = "order.exchange";
    public static final String orderqueue = "order.queue";

    public static final String orderroutingKey = "order.routingKey";

    public static final String retryQueue = "retry.order.queue";
    public static final String retryExchange = "retry.exchange";
    public static final String retryRoutingKey = "retry.routingKey";

    public static final  String Dexchange = "dead-exchange";
    public static final String Dqueue = "dead-order.queue";
    public static final String DroutingKey = "dead-routingKey";



    //now we are sending just   message intead we should send dto but rabbitmq doesn't
    //direclty understand java objects so we need to converrt them to json using
    //message converter specifically Jackson2JsonMessageConverter


    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }


    //now we can send dto's
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(orderexchange,true,false);
    }
    @Bean
    public Queue queue(){
//        return new Queue(queue,true);
        return QueueBuilder.durable(orderqueue)
                .withArgument("x-dead-letter-exchange",Dexchange)
                .withArgument("x-dead-letter-routing-key",DroutingKey)
                .build();
    }
    @Bean
    public Binding  orderBinding(DirectExchange directExchange,Queue queue){
        return BindingBuilder.bind(queue).to(directExchange).with(orderroutingKey);
    }
    @Bean
    public Queue retryQueue(){
        return QueueBuilder.durable(retryQueue)
                .withArgument("x-message-ttl",10000)
                .withArgument("x-dead-letter-exchange",orderexchange)
                .withArgument("x-dead-letter-routing-key",orderroutingKey)
                .build();
    }
    @Bean
    public DirectExchange retryExchange(){
        return new DirectExchange(retryExchange,true,false);
    }
    @Bean
    public Binding retryBinding(Queue retryQueue, DirectExchange retryExchange){
        return BindingBuilder.bind(retryQueue).to(retryExchange).with(retryRoutingKey);
    }

    @Bean
    public Queue deadQueue(){
        return new Queue(Dqueue,true);
    }
    @Bean
    public DirectExchange deadExchange(){
        return new DirectExchange(Dexchange,true,false);
    }
    @Bean
    public Binding  deadBinding(DirectExchange deadExchange,Queue deadQueue){
        return BindingBuilder.bind(deadQueue).to(deadExchange).with(DroutingKey);
    }
}
