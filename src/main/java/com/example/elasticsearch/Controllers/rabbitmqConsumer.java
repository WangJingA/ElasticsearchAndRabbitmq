package com.example.elasticsearch.Controllers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class rabbitmqConsumer {
    @Autowired
    RabbitTemplate rabbitTemplate;
    @GetMapping("consumerMessage")
    public String consumerMessage(){
        return "";
    }
}
