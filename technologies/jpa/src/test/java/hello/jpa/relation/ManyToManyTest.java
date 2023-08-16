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

    @Transactional
    @Test
    void test2() {
        Product product = new Product("product1");
        em.persist(product);

        Member member = new Member("member");
        member.addProduct(product);
        em.persist(member);

        em.flush();
        em.clear();

        Member findMember = em.find(Member.class, member.getId());

        findMember.getProducts().forEach(
                p -> System.out.println(p.getName())
        );
    }

    @Transactional
    @Test
    void test3() {
        Product product = new Product("product1");
        em.persist(product);

        Member member = new Member("member");
        member.addProduct(product);
        em.persist(member);

        em.flush();
        em.clear();

        System.out.println("=================findMember=================");
        Member findMember = em.find(Member.class, member.getId());
        findMember.getProducts().forEach(
                p -> System.out.println(p.getName())
        );

        System.out.println("=================findProduct=================");
        Product findProduct = em.find(Product.class, product.getId());
        findProduct.getMembers().forEach(
                m -> System.out.println(m.getUsername())
        );
    }
}
