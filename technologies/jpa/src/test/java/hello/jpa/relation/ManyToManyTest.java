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

        MemberProduct memberProduct = new MemberProduct(member, product);
        memberProduct.setOrderAmount(2);
        em.persist(memberProduct);

        em.flush();
    }


    @Transactional
    @Test
    void test2() {
        Member member = new Member("member");
        em.persist(member);

        Product product = new Product("product");
        em.persist(product);

        MemberProduct memberProduct = new MemberProduct(member, product);
        memberProduct.setOrderAmount(2);
        em.persist(memberProduct);

        em.flush();
        em.clear();

        MemberProduct.MemberProductId memberProductId = new MemberProduct.MemberProductId(member.getId(), product.getId());
        MemberProduct findMemberProduct = em.find(MemberProduct.class, memberProductId);
        Member findMember = findMemberProduct.getMember();
        Product findProduct = findMemberProduct.getProduct();

        System.out.println(findMember.getUsername());
        System.out.println(findProduct.getName());
        System.out.println(findMemberProduct.getOrderAmount());
    }
}
