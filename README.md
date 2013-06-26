Samples demonstrating Spring AMQP (RabbitMQ) and Spring WebSocket (JSR-356) support.

## Static RabbitMQ configuration

Declare static exchanges, queues, and bindings in XML:
[ac-rabbitmq.xml](src/main/resources/ac-rabbitmq.xml).

## Dynamic RabbitMQ configuration

Declare queues, bindings, and listeners programmatically:
[ListenerEndpoint](src/main/java/com/ndpar/spring/websocket/ListenerEndpoint.java).

## WebSockets annotations

Take a look at
[ListenerEndpoint](src/main/java/com/ndpar/spring/websocket/ListenerEndpoint.java),
[SenderEndpoint](src/main/java/com/ndpar/spring/websocket/SenderEndpoint.java).

## Testing

- Build the app: `mvn clean package`
- Deploy it to Tomcat
- Open [listener](http://localhost:8080/rabbitmq-spring-demo/receive.html) in a browser window
- Click *Connect* button
- Open [sender](http://localhost:8080/rabbitmq-spring-demo/send.html) in another browser window
- Click *Connect* button
- Click *Send* button

## Tomcat

Tomcat provides early JSR-356 support (WebSocket API for Java).
You'll need to build the latest Tomcat source from trunk, which is relatively simple.

Check out Tomcat trunk:

    mkdir tomcat
    cd tomcat
    svn co http://svn.apache.org/repos/asf/tomcat/trunk/
    cd trunk

Create `build.properties` in the trunk directory with similar content:

    # ----- Default Base Path for Dependent Packages -----
    # Replace this path with the path where dependencies binaries should be downloaded
    base.path=~/dev/sources/apache/tomcat/download

Run the ant build:

    ant clean
    ant

A usable Tomcat installation can be found in `output/build`

## Resources

Spring AMQP - [Reference Documentation](http://static.springsource.org/spring-amqp/reference/htmlsingle/)