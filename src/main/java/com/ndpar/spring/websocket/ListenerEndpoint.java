package com.ndpar.spring.websocket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.server.endpoint.SpringConfigurator;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.List;
import java.util.Map;

@ServerEndpoint(value = "/listenerEndpoint", configurator = SpringConfigurator.class)
public class ListenerEndpoint implements MessageListener {

    public static final String QUEUE_PREFIX = "websocket.";
    public static final String KEY = "key";

    protected final Log log = LogFactory.getLog(getClass());

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Autowired
    private ConnectionFactory connectionFactory;

    private Session session;
    private SimpleMessageListenerContainer container;

    @OnOpen
    public void newSession(Session session) {
        log.debug("Opened new session in instance " + this);
        this.session = session;

        String queueName = QUEUE_PREFIX + session.getId();
        Queue queue = new Queue(queueName, false, false, true);
        amqpAdmin.declareQueue(queue);

        Map<String, List<String>> params = session.getRequestParameterMap();
        if (params.containsKey(KEY)) {
            String key = params.get(KEY).get(0);
            Binding binding = new Binding(queueName, Binding.DestinationType.QUEUE, "amq.topic", key, null);
            amqpAdmin.declareBinding(binding);
        } else {
            log.warn(String.format("No %s parameter provided", KEY));
        }

        container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueues(queue);
        container.setMessageListener(this);
        container.start();
    }

    @OnClose
    public void sessionClose(Session session) {
        container.stop();
    }

    @Override
    public void onMessage(Message message) {
        try {
            String text = new String(message.getBody());
            log.debug(String.format("Received message %s with payload %s", message, text));
            session.getBasicRemote().sendText(text);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @OnError
    public void handleError(Throwable t) {
        log.error("handleError", t);
    }
}
