package com.example.elasticsearch.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@RabbitListener(queues = "topicQueue.test1")
public class TopicQueueListener {
    @RabbitHandler
    public void process(Map resultMap){
        System.out.println(resultMap.toString());
    }
}
