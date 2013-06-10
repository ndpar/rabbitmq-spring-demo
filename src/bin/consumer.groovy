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

queueName = 'myQueue'

channel.queueDeclare queueName, false, true, true, [:]
channel.queueBind queueName, 'amq.fanout', 'myRoutingKey'

def consumer = new QueueingConsumer(channel)
channel.basicConsume queueName, false, consumer

while (true) {
    delivery = consumer.nextDelivery()
    println "Received message: ${new String(delivery.body)}"
    channel.basicAck delivery.envelope.deliveryTag, false
}
channel.close()
conn.close()