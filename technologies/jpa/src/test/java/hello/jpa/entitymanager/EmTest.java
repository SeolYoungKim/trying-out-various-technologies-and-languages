package hello.jpa.entitymanager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
public class EmTest {
    @Autowired
    TestRepository testRepository;

    @DisplayName("서로 다른 스레드가 요청을 보내면 모두 다른 EntityManager를 사용한다.")
    @Test
    void test() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CountDownLatch countDownLatch = new CountDownLatch(50);
        for (int i = 0; i < 50; i++) {
            executorService.submit(() -> {
                testRepository.printEntityManager();
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        executorService.shutdown();
    }
}
