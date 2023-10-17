package hello.jpa.nplus1;

import hello.jpa.relation.Member;
import hello.jpa.relation.Order;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class NPlusOneTest {
    @Autowired
    private EntityManager em;

    @DisplayName("즉시 로딩으로 설정하고 아래의 테스트를 돌리면...")
    @Transactional
    @Test
    void test1() {
        Member member = new Member("member");
        member.addOrder(new Order(member));
        member.addOrder(new Order(member));
        member.addOrder(new Order(member));

        em.persist(member);

        em.flush();
        em.clear();

        List<Member> members = em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    @DisplayName("지연 로딩으로 설정하고 아래의 테스트를 돌리면...")
    @Transactional
    @Test
    void test2() {
        Member member = new Member("member");
        Member member2 = new Member("member");
        Member member3 = new Member("member");
        Member member4 = new Member("member");

        Order order1 = new Order(member);
        member.addOrder(order1);

        Order order2 = new Order(member);
        member.addOrder(order2);

        Order order3 = new Order(member);
        member.addOrder(order3);

        em.persist(member);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        em.persist(order1);
        em.persist(order2);
        em.persist(order3);

        em.flush();
        em.clear();

        List<Member> members = em.createQuery("select m from Member m", Member.class)
                .getResultList();
        for (Member m : members) {
            System.out.println("m.getOrders().size() = " + m.getOrders().size());
        }

//        Member findMember = em.find(Member.class, member.getId());
//        for (Order order : findMember.getOrders()) {
//            System.out.println("order.getId() = " + order.getId());
//        }
    }
}
