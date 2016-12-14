## This is a sample project for developing a Web Service starting from Java code and using JBossWS.

The project has the usual Maven structure and is built with:

> mvn clean package

To deploy the generated WAR archive to a running WildFly instance use:

> mvn wildfly:deploy

Once the WAR is deployed, the integration testsuite is run with:

> mvn integration-test

Finally, you can undeploy the WAR with:

> mvn wildfly:undeploy

### Deploy application to Cloudfoundry

This sample provisions jboss buildpack to run webservices.
See manifest.yml for cf deployment properties.

Deploy an application

> cf push

Verify that application was deployed by navigating to wsdl endpoint (target your specific url)
http://ws-soap.cfapps.io/HelloWorldService?wsdl

### Consume webservices deployed to Cloudfoundry

```
String endpoint = "http://ws-soap.cfapps.io/";
HelloWorldService service = new HelloWorldService(new URL(endpoint + "HelloWorldService?wsdl"));
HelloWorld port = service.getHelloWorldPort();

BindingProvider bindingProvider = (BindingProvider) port;
bindingProvider.getRequestContext()
        .put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
        
Assert.assertEquals("Hello John", port.sayHi("John"));
```        
