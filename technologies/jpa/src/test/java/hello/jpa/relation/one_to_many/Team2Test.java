package hello.jpa.relation.one_to_many;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class Team2Test {
    @Autowired
    private EntityManager em;

    @Transactional
    @Test
    void test() {
        Member2 member1 = new Member2("member1");
        Member2 member2 = new Member2("member2");

        Team2 team2 = new Team2("team1");
        team2.addMember(member1);
        team2.addMember(member2);

        em.persist(member1);
        em.persist(member2);
        em.persist(team2);
    }
}