#!/usr/bin/env groovy

import com.rabbitmq.client.*

@Grab(group='com.rabbitmq', module='amqp-client', version='3.1.0')
factory = new ConnectionFactory([
    username: 'guest',
    password: 'guest',
    virtualHost: '/',
    requestedHeartbeat: 0
])
conn = factory.newConnection(new Address('localhost', 5672))
channel = conn.createChannel()

channel.basicPublish 'amq.fanout', 'myRoutingKey', null, "Hello, world!".bytes

channel.close()
conn.close()