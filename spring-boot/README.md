Camel with Spring
-----------------

A Simple Demo to illustrate the Camel with Spring.

Spring Boot Example
-------------------

The example generates messages using timer trigger, writes them to the standard output and the mock
endpoint (for testing purposes).

### Build
You will need to compile this example first:

	mvn install

### Run
To run the example type

	mvn spring-boot:run

You can also execute the JAR directly:

	java -jar target/spring-boot.jar

You will see the message printed to the console every 2 seconds.
Health is also shown every 5 seconds.
(Configurable via application.properties)

To stop the example hit <kbd>ctrl</kbd>+<kbd>c</kbd>

### Remote Shell

The example ships with remote shell enabled which includes the Camel commands as well, so you can SSH into the running Camel application and use the camel commands to list / stop routes etc.

You can SSH into the JVM using

    ssh -p 2000 user@localhost

And then use `password` when the server will prompt to encode the password.

A nice [blog](http://www.davsclaus.com/2015/11/using-camel-commands-to-manage-spring.html) and [video](https://vimeo.com/145657347) explains about the remote shell capabilities. 
Tab completion is available !!!

But, command `help` sometimes gives **NullPointer Exception** because in 

    g:"org.crashub" a:"crash.shell"

a developer has hard-coded the systemPath of tools.jar !!!  