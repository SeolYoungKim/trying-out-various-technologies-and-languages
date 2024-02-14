package hello.redis;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CacheTest {
    @LocalServerPort
    private int port;

    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        restTemplate = new RestTemplateBuilder()
                .rootUri("http://localhost:" + port)
                .build();
    }

    @Test
    void name() {
        try (ExecutorService threadPool = Executors.newFixedThreadPool(10)) {
            for (int i = 0; i < 10; i++) {
                threadPool.submit(() -> {
                    restTemplate.getForEntity("/posts", String.class);
                });
            }
        }
    }
}
