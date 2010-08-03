package com.ndpar.spring.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class MessageHandler implements MessageListener {

    @Override
    public void onMessage(Message message) {
        System.out.println("Received message: " + message);
        System.out.println("Text: " + new String(message.getBody()));
    }
}
