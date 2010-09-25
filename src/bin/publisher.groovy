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

channel.basicPublish 'ndpar.topic', 'NDPAR.GROOVY.GROOVY', null, "Hello, world!".bytes

channel.close()
conn.close()
