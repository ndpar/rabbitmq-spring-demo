Samples demonstrating Spring WebSocket support, currently in development for Spring Framework 4.0.

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