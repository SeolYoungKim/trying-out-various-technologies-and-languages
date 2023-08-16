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
        Member member = new Member("member");
        em.persist(member);

        Product product = new Product("product");
        em.persist(product);

        Order order = new Order(member, product);
        order.setOrderAmount(2);
        em.persist(order);

        em.flush();
    }


    @Transactional
    @Test
    void test2() {
        Member member = new Member("member");
        em.persist(member);

        Product product = new Product("product");
        em.persist(product);

        Order order = new Order(member, product);
        order.setOrderAmount(2);
        em.persist(order);

        em.flush();
        em.clear();

        Order findOrder = em.find(Order.class, order.getId());
        Member findMember = findOrder.getMember();
        Product findProduct = findOrder.getProduct();

        System.out.println(findMember.getUsername());
        System.out.println(findProduct.getName());
        System.out.println(findOrder.getOrderAmount());
    }
}
