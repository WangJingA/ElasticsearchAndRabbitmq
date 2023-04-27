package com.example.elasticsearch.Controllers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class rabbitmqProducor {
    @Autowired
    RabbitTemplate template;
    @PostMapping("pushMessage")
    public String productMessage(){
        Map<String,String> map = new HashMap<>();
        map.put("id", UUID.randomUUID().toString());
        map.put("username","李四");
        map.put("password","23545");
        template.convertAndSend("directExchange","directExchange",map);
        map.clear();
        map.put("id",UUID.randomUUID().toString());
        map.put("des","this is topic exchange1");
        map.put("name","topicExchange");
        template.convertAndSend("topicExchange","topicQueue.test1",map);
        map.clear();
        map.put("id",UUID.randomUUID().toString());
        map.put("des","this is topic exchange2");
        map.put("name","topicExchange");
        template.convertAndSend("topicExchange","topicQueue.test2",map);
        return "send message success";
    }
}
