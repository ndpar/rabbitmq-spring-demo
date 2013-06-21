package com.ndpar.spring.rabbitmq;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;

@Service
@ManagedResource
public class MessageSender {

    protected final Log log = LogFactory.getLog(getClass());

    @Autowired
    private AmqpTemplate template;

    @ManagedOperation
    public void send(String text) {
        send("amq.fanout", "NDPAR.SPRING.JAVA", text);
    }

    @ManagedOperation
    public void send(String exchange, String key, String text) {
        log.debug(String.format("%s -> %s: %s", key, exchange, text));
        template.convertAndSend(exchange, key, text);
    }
}
