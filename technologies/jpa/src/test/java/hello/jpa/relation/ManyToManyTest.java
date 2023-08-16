package hello.jpa.relation;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class ManyToManyTest {
    @Autowired
    private EntityManager em;

    @Transactional
    @Test
    void test1() {
        Product product = new Product("product1");
        em.persist(product);

        Member member = new Member("member");
        member.addProduct(product);
        em.persist(member);

        em.flush();
    }
}
