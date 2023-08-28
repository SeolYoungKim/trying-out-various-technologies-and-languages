package hello.jpa.proxy;

import hello.jpa.relation.Member;
import hello.jpa.relation.Team;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class ProxyTest {
    @Autowired
    private EntityManager em;

    @Transactional
    @Test
    void 프록시_객체의_식별자_조회() {
        Member member = new Member("name");
        em.persist(member);
        em.clear();

        // 프록시 객체는 내부에 Id값을 보관한다. 따라서, 프록시 초기화가 발생하지 않는다.
        System.out.println("======================식별자 조회======================");
        Member reference = em.getReference(Member.class, 1L);
        System.out.println("타입=" + reference.getClass());
        System.out.println("Id=" + reference.getId());
    }
}
