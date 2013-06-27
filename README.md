Samples demonstrating Spring AMQP (RabbitMQ) and Spring WebSocket (JSR-356) support.

## Static RabbitMQ configuration

Declare exchanges, queues, bindings, etc. statically in XML:
[ac-rabbitmq.xml](src/main/resources/ac-rabbitmq.xml).

## Dynamic RabbitMQ configuration

Declare queues, bindings, and listeners programmatically:
[ListenerEndpoint](src/main/java/com/ndpar/spring/websocket/ListenerEndpoint.java#L44-46).

## WebSockets

Java:
[ListenerEndpoint](src/main/java/com/ndpar/spring/websocket/ListenerEndpoint.java#L11-15),
[SenderEndpoint](src/main/java/com/ndpar/spring/websocket/SenderEndpoint.java).

JavaScript:
[receive.html](src/main/webapp/receive.html#L18-34),
[send.html](src/main/webapp/send.html#L19-35).

## Running demo

- Build the app: `mvn clean package`
- Deploy it to Tomcat
- Open [listener](http://localhost:8080/rabbitmq-spring-demo/receive.html) in a browser window
- Click *Connect* button
- Open [sender](http://localhost:8080/rabbitmq-spring-demo/send.html) in another browser window
- Click *Connect* button
- Click *Send* button

## Tomcat

Tomcat provides early JSR-356 support (WebSocket API for Java).
You'll need to build the latest Tomcat from sources.

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

- Spring AMQP — [Reference Documentation](http://static.springsource.org/spring-amqp/reference/htmlsingle/)
- Spring WebSockets — Rossen Stoyanchev's [blog](http://blog.springsource.org/2013/05/22/spring-framework-4-0-m1-websocket-support/)
