package pl.piomin.samples.callme;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static  org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = { "POD_NAME=abc", "POD_NAMESPACE=default"}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CallmeControllerTests {

    @LocalServerPort
    int port;
    @Autowired
    TestRestTemplate restTemplate;
    @Autowired(required = false)
    BuildProperties buildProperties;

    @Test
    void ping() {
        String version = buildProperties != null ? buildProperties.getVersion() : "null";
        String response = restTemplate.getForObject("/callme/ping", String.class);
        assertNotNull(response);
        assertEquals("callme-service v" + version + " (id=1): abc in default", response);
    }
}
