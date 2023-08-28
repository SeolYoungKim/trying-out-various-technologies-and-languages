package hello.jpa.proxy;

import hello.jpa.relation.Member;
import hello.jpa.relation.Order;
import hello.jpa.relation.Product;
import hello.jpa.relation.Team;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceUnitUtil;
import org.assertj.core.api.Assertions;
import org.hibernate.collection.spi.PersistentBag;
import org.hibernate.proxy.HibernateProxy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class ProxyTest {
    @Autowired
    private EntityManager em;

    @Transactional
    @Test
    void 프록시_객체의_식별자_조회() {
        Member member = new Member("name");
        em.persist(member);
        em.clear();  // 영속성 컨텍스트를 비워줘야 프록시 객체가 조회된다. (영속성 컨텍스트에 실제 객체가 있다면 프록시가 아닌 실제 객체가 조회된다.)

        // 프록시 객체는 내부에 Id값을 보관한다. 따라서, 프록시 초기화가 발생하지 않는다.
        System.out.println("======================식별자 조회======================");
        Member reference = em.getReference(Member.class, 1L);
        System.out.println("타입=" + reference.getClass());
        System.out.println("Id=" + reference.getId());

        EntityManagerFactory emf = em.getEntityManagerFactory();
        assertThat(emf.getPersistenceUnitUtil().isLoaded(reference)).isFalse();
    }

    @Transactional
    @Test
    void 영속성_컨텍스트에_존재하는_객체의_프록시_조회() {
        Member member = new Member("name");
        em.persist(member);

        Member reference = em.getReference(Member.class, 1L);
        System.out.println("타입=" + reference.getClass());
    }

    @Transactional
    @Test
    void 프록시와_컬렉션_래퍼() {
        Member member = new Member("name");
        em.persist(member);

        Product product = new Product("product");
        em.persist(product);

        Order order = new Order(member, product);
        em.persist(order);

        member.setOrders(List.of(order));

        em.flush();
        em.clear();

        System.out.println("======================컬렉션 래퍼 조회======================");
        Member findMember = em.find(Member.class, 1L);
        List<Order> orders = findMember.getOrders();
        System.out.println("타입=" + orders.getClass().getName());

        System.out.println("======================초기화======================");
        orders.get(0);
    }
}
