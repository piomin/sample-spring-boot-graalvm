package pl.piomin.samples.callme;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Test
    void ping() {
        String response = restTemplate.getForObject("/callme/ping", String.class);
        assertNotNull(response);
        assertEquals("callme-service v1.0-SNAPSHOT (id=1): abc in default", response);
    }
}
