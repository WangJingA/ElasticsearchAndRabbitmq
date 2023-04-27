package com.example.elasticsearch.listener;


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * 监听直连队列
 */
@Configuration
@RabbitListener(queues = "testQueue")
public class RedictQueueListener {
    @RabbitHandler
    public void process(Map testMessage){
        System.out.println(testMessage.toString());
    }
}
