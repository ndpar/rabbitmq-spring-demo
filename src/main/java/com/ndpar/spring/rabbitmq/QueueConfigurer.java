package com.ndpar.spring.rabbitmq;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Optional class. Needed only if queue does not exist in RabbitMQ server.
 */
public class QueueConfigurer {

    public static final String EXCHANGE = "ndpar.topic";
    public static final String QUEUE = "ndpar.spring.client";
    public static final String BINDING_KEY = "NDPAR.SPRING.#";

    @Autowired
    private AmqpAdmin admin;

    @PostConstruct
    public void init() {
        Queue queue = new Queue(QUEUE);
        TopicExchange exchange = new TopicExchange(EXCHANGE);
        Binding binding = new Binding(queue, exchange, BINDING_KEY);

        admin.declareQueue(queue);
        admin.declareExchange(exchange);
        admin.declareBinding(binding);
    }

    @PreDestroy
    public void destroy() {
        admin.deleteQueue(QUEUE, true, true);
    }
}
