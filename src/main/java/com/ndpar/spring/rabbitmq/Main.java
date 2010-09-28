package com.ndpar.spring.rabbitmq;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("/applicationContext.xml");
        context.getBean(MessageHandler.class);
    }
}
