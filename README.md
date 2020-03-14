## Prerequisites

Run RabbitMQ locally with default settings

    rabbitmq-server

## Run Application

    mvn clean spring-boot:run

## Examples

### Randez-Vous

Run the following commands in separate terminal windows

    curl -X POST http://localhost:8080/alice \
        -H 'Content-Type: application/json' \
        -d '{"id": "123","message":"Hello from Alice"}'

    curl -X POST http://localhost:8080/bob \
        -H 'Content-Type: application/json' \
        -d '{"id": "123","message":"Hello from Bob"}'

- Try to change sequence of those messages.
- Try to send only one message and wait for 20 seconds.
- Compare timestamps of the responses.
- Monitor queues using this command


    rabbitmqctl list_queues

## Resources

Spring AMQP - [Reference Documentation](https://docs.spring.io/spring-amqp/reference/html/)