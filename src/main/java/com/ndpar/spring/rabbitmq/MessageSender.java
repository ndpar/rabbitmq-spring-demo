package com.ndpar.spring.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;

@Service
@ManagedResource
public class MessageSender {

    @Autowired
    private AmqpTemplate template;

    @ManagedOperation
    public void send(String text) {
        send("amq.fanout", "NDPAR.SPRING.JAVA", text);
    }

    @ManagedOperation
    public void send(String exchange, String key, String text) {
        template.convertAndSend(exchange, key, text);
    }
}
