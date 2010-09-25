package com.ndpar.spring.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class MessageSender {

    @Autowired
    private AmqpTemplate template;

    public void send(String text) {
        template.convertAndSend("NDPAR.SPRING.JAVA", text);
    }
}
