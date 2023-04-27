package com.example.elasticsearch.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息确认手动和自动设置
 * 此配置配置在消费者模块中
 */
@Configuration
public class MessageListenerConfig {
    @Autowired
    CachingConnectionFactory connectionFactory;
    @Autowired
    MyAckReceiver ackReceiver;
    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer(){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConsumerBatchEnabled(true);
        container.setConcurrentConsumers(1);
        //设置手动应答
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setQueueNames("topicQueue.test1");
        container.setQueueNames("topicQueue.test2");
        container.setQueueNames("testQueue");
        container.setMessageListener(ackReceiver);
        return container;
    }
}
