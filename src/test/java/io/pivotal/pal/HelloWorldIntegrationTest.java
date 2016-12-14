package io.pivotal.pal;

import java.net.URL;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import client.HelloWorld;
import client.HelloWorldService;
import client.Person;

import javax.xml.ws.BindingProvider;

public class HelloWorldIntegrationTest {

    @Test
    public void testHelloWorld() throws Exception {
    	HelloWorldService service = new HelloWorldService(new URL("http://localhost:8080/ws-soap/HelloWorldService?wsdl"));
    	HelloWorld port = service.getHelloWorldPort();
        verifyResponse(port);
    }

    @Test
    @Ignore
    public void testHelloWorldRemote() throws Exception {
        HelloWorldService service = new HelloWorldService(new URL("http://ws-soap.cfapps.io/HelloWorldService?wsdl"));
        HelloWorld port = service.getHelloWorldPort();
        BindingProvider bindingProvider = (BindingProvider) port;
        String endpoint = "http://ws-soap.cfapps.io/";
        bindingProvider.getRequestContext()
                .put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
        verifyResponse(port);
    }

    private void verifyResponse(HelloWorld port) {
        Assert.assertEquals("Hello John", port.sayHi("John"));
        Person p = new Person();
        p.setName("Anne");
        p.setSurname("Li");
        Assert.assertEquals("Greetings Anne Li", port.greetings(p));
    }
}
