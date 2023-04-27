package com.example.elasticsearch.config;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;




@Configuration
public class TopicRabbitConfig {
    @Bean
    public Queue topicQueue(){
        return new Queue("topicQueue.test1",true);
    }

    @Bean
    public Queue topicQueueSecond(){
        return new Queue("topicQueue.test2",true);
    }

    @Bean
    public Exchange topicExchange(){
        return new TopicExchange("topicExchange");
    }

    @Bean
    Binding bindingExchangeWithTopic(){
        return BindingBuilder.bind(topicQueue()).to(topicExchange()).with("topicQueue.test1").noargs();
    }

    @Bean
    Binding bindingExchangeWithTopic2(){
        return BindingBuilder.bind(topicQueueSecond()).to(topicExchange()).with("topicQueue.test2").noargs();
    }

    @Bean
    Binding bindingExchangeWithTopic3(){
        return BindingBuilder.bind(topicQueueSecond()).to(topicExchange()).with("topicQueue.#").noargs();
    }
}
