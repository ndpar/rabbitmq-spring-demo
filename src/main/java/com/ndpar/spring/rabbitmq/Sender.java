package com.ndpar.spring.rabbitmq;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Sender {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("/applicationContext.xml");
        MessageSender messageSender = context.getBean(MessageSender.class);
        messageSender.send("Hello from Spring-AMQP");
    }
}
