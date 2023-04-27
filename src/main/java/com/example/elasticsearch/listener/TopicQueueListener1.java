package com.example.elasticsearch.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@RabbitListener(queues = "topicQueue.test2")
public class TopicQueueListener1 {
    @RabbitHandler
    public void process(Map map){
        System.out.println(map.toString());
    }
}
