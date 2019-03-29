# Camunda Thorntail extension

This Camunda community extension provides Thorntail fractions, which can be used to easily include Camunda in your Thorntail applications.
You would need to add [Camunda BOM](https://docs.camunda.org/get-started/apache-maven/) and "Thorntail fraction(s)" to your POM file. Available fractions are:

* `camunda-bpm-thorntail-fraction-platform` for Camunda engine
* `camunda-bpm-thorntail-fraction-rest` for Camunda REST API
* `camunda-bpm-thorntail-fraction-webapp(-ee)` for Camunda Webapps

Example:

```xml
    <dependency>
      <groupId>io.thorntail</groupId>
      <artifactId>camunda-bpm-thorntail-fraction-webapp</artifactId>
      <version>${fraction.version}</version>
    </dependency>
```

Please also check [example project](https://github.com/camunda/camunda-bpm-wildfly-swarm/tree/master/example).

## Enterprise version

To use enterprise version of Camunda:

1. Use enterprise Camunda version, when adding Camunda BOM, e.g. `7.9.0-ee`.
2. Use fraction `camunda-bpm-thorntail-fraction-webapp-ee` to include Camunda Enterprise Webapps.

## Resources

* [Issue Tracker](https://github.com/camunda/camunda-bpm-wildfly-swarm/issues) - if you have an idea for a new feature or want to file a bug ... this is the place to go.
* [Camunda Contributing Guidelines](https://github.com/camunda/camunda-bpm-platform/blob/master/CONTRIBUTING.md) - check this if you want to contribute
