package com.ndpar.spring.rabbitmq;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class MessageHandler implements MessageListener {

    protected final Log log = LogFactory.getLog(getClass());

    @Override
    public void onMessage(Message message) {
        log.info("Received message: " + message);
        log.info("Text: " + new String(message.getBody()));
    }
}
