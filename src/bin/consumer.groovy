import com.rabbitmq.client.*

@Grab(group='com.rabbitmq', module='amqp-client', version='1.8.1')
factory = new ConnectionFactory(
    username: 'guest',
    password: 'guest',
    virtualHost: '/',
    host: 'lab.ndpar.com',
    port: 5672
)
conn = factory.newConnection()
channel = conn.createChannel()

exchangeName = 'ndpar.topic'; queueName = 'ndpar.groovy.client'

channel.exchangeDeclare exchangeName, 'topic'
channel.queueDeclare queueName, false, false, false, null
channel.queueBind queueName, exchangeName, 'NDPAR.GROOVY.#'

def consumer = new QueueingConsumer(channel)
channel.basicConsume queueName, false, consumer

while (true) {
    delivery = consumer.nextDelivery()
    println "Received message: ${new String(delivery.body)}"
    channel.basicAck delivery.envelope.deliveryTag, false
}
channel.close()
conn.close()
