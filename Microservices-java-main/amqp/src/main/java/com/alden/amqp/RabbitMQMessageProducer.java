package com.alden.amqp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class RabbitMQMessageProducer {
    private AmqpTemplate amqpTemplate;

    public void publish(Object message, String exchange, String routingKey) {
        log.info("Publishing message to exchange: {}, routing key: {}, message: {}", exchange, routingKey, message);
        amqpTemplate.convertAndSend(exchange, routingKey, message);
        log.info("Published message to exchange: {}, routing key: {}, message: {}", exchange, routingKey, message);
    }
}
