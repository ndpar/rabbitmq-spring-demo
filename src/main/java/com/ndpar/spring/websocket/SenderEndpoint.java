package com.ndpar.spring.websocket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.server.endpoint.SpringConfigurator;

import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import static com.ndpar.spring.websocket.Constants.*;

@ServerEndpoint(value = "/senderEndpoint", configurator = SpringConfigurator.class)
public class SenderEndpoint {

    protected final Log log = LogFactory.getLog(getClass());

    @Autowired
    private AmqpTemplate template;

    @OnOpen
    public void newSession() {
        log.debug("Opened new session in instance " + this);
    }

    @OnMessage
    public void sendTextMessage(Session session, String msg, boolean last) {
        log.info("Message: " + msg);
        String[] tokens = msg.split(";");
        template.convertAndSend(TOPIC, tokens[0], tokens[1]);
    }

    @OnError
    public void handleError(Throwable t) {
        log.error(t.getMessage(), t);
    }
}
