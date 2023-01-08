package pl.piomin.samples.caller;

import io.specto.hoverfly.junit.core.Hoverfly;
import io.specto.hoverfly.junit5.HoverflyExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static io.specto.hoverfly.junit.core.SimulationSource.dsl;
import static io.specto.hoverfly.junit.dsl.HoverflyDsl.service;
import static io.specto.hoverfly.junit.dsl.ResponseCreators.success;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(properties = { "POD_NAME=abc", "POD_NAMESPACE=default"}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(HoverflyExtension.class)
public class CallerControllerTests {

    @LocalServerPort
    int port;
    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void ping(Hoverfly hoverfly) {
        hoverfly.simulate(dsl(
                service("http://callme-service.serverless.svc.cluster.local")
                        .get("/callme/ping")
                        .willReturn(success("callme-service v1.0-SNAPSHOT (id=1): abc in default", "text/plain"))));

        String response = restTemplate.getForObject("/caller/ping", String.class);
        assertNotNull(response);

        String c = "caller-service(id=1): abc in default is calling callme-service v1.0-SNAPSHOT (id=1): abc in default";
        assertEquals(c, response);
    }
}