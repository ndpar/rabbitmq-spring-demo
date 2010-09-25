package com.ndpar.spring.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;

public class MessageSender {

    @Autowired
    private AmqpTemplate template;

    @ManagedOperation
    public void send(String text) {
        send("NDPAR.SPRING.JAVA", text);
    }

    @ManagedOperation
    public void send(String key, String text) {
        template.convertAndSend(key, text);
    }
}
