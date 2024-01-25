package hello.jpa.jointest;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class BannerTest {
    @Autowired
    private BannerJpaRepository bannerJpaRepository;
    @Autowired
    private EntityManager em;

    @Transactional
    @Test
    void test() {


        System.out.println("==============================");

    }
}