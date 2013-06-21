package com.ndpar.spring.websocket;

import com.ndpar.spring.rabbitmq.MessageSender;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.server.endpoint.SpringConfigurator;

import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/senderEndpoint", configurator = SpringConfigurator.class)
public class SenderEndpoint {

    protected final Log log = LogFactory.getLog(getClass());

    @Autowired
    private MessageSender messageSender;

    @OnOpen
    public void newSession() {
        log.debug("Opened new session in instance " + this);
    }

    @OnMessage
    public void echoTextMessage(Session session, String msg, boolean last) {
        log.info("Sending message: " + msg);
        messageSender.send("amq.topic", "999999", msg);
    }

    @OnError
    public void handleError(Throwable t) {
        log.error(t.getMessage(), t);
    }
}
