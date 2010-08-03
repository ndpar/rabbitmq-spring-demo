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

channel.exchangeDelete 'myExchange'
channel.queueDelete 'myQueue'

channel.close()
conn.close()
