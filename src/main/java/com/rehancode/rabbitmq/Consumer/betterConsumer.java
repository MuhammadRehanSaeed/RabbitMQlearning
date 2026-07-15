package com.rehancode.rabbitmq.Consumer;


import com.rabbitmq.client.Channel;
import com.rehancode.rabbitmq.Configuration.RabbitMqConfig;

import com.rehancode.rabbitmq.DTO.OrderCreatedEvent;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class betterConsumer {
    private final RabbitTemplate rabbitTemplate;
    public betterConsumer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = RabbitMqConfig.orderqueue)
    public void receiveMessage(OrderCreatedEvent event,
                               Channel channel,
                               Message message
    ) throws IOException {
        System.out.println(event.getEmail());
        try{
            //check here if messgageid is already processed to be idempotent

//                if(repo.existbyid(event.getmessageid()))
//                {
//                    channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
//                    return;
//                }
            //ok
            int i=10/0;
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }catch(Exception e){

            try {


                if(event.getRetryCount() < 3){

                    event.setRetryCount(
                            event.getRetryCount()+1
                    );

                    rabbitTemplate.convertAndSend(
                            RabbitMqConfig.retryExchange,
                            RabbitMqConfig.retryRoutingKey,
                            event
                    );

                }
                else{

                    rabbitTemplate.convertAndSend(
                            RabbitMqConfig.Dexchange,
                            RabbitMqConfig.DroutingKey,
                            event
                    );

                }


                channel.basicAck(
                        message.getMessageProperties()
                                .getDeliveryTag(),
                        false
                );


            } catch(Exception ex){

                channel.basicNack(
                        message.getMessageProperties()
                                .getDeliveryTag(),
                        false,
                        true
                );

            }

        }

    }
}
