# Camunda Wildfly Swarm Example

This example shows, how Camunda can be used within Wildfly Swarm application. Specifically:
* the datasource is configured in [`project-defaults.yml`](https://github.com/camunda/camunda-bpm-wildfly-swarm/blob/master/example/src/main/resources/project-defaults.yml)
* the process archive is declared and deployed
* the default engine configuration is customized within [`ExampleMain` class](https://github.com/camunda/camunda-bpm-wildfly-swarm/blob/master/example/src/main/java/org/wildfly/swarm/camunda/bpm/ExampleMain.java). 
In case you don't provide such main class, the engine with default configuration will be used. For other configuration possibilities, please consult the Wildfly Swarm documentation.

To run the example execute:

```
mvn clean wildfly-swarm:run
```

or

```text
mvn clean install
cd target
java -jar example-swarm.jar 
```    

Then navigate to [http://localhost:8080/camunda](http://localhost:8080/camunda) in your browser.