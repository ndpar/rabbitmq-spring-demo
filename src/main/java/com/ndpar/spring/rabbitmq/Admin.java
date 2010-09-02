package com.ndpar.spring.rabbitmq;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Admin {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("/applicationContext-admin.xml");
        new Admin().declareEverything(context.getBean(RabbitAdmin.class));
    }

    private void declareEverything(AmqpAdmin admin) {
        Queue queue = new Queue("myQueue");
        DirectExchange exchange = new DirectExchange("myExchange");
        Binding binding = new Binding(queue, exchange, "myRoutingKey");

        admin.declareQueue(queue);
        admin.declareExchange(exchange);
        admin.declareBinding(binding);
    }
}
