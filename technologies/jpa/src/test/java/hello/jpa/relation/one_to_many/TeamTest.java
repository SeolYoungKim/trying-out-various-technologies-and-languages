package hello.jpa.relation.one_to_many;

import hello.jpa.relation.Member;
import hello.jpa.relation.Team;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class TeamTest {
    @Autowired
    private EntityManager em;

    @Transactional
    @Test
    void test() {
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");

        Team team = new Team("team1");
        team.addMember(member1);
        team.addMember(member2);

        em.persist(member1);
        em.persist(member2);
        em.persist(team);

        em.flush();
    }
}