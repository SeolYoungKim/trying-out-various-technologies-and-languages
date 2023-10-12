package hello.jpa.entitymanager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.internal.SessionImpl;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Repository
public class TestRepository {
    private final EntityManager entityManager;

    public TestRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void printEntityManager() {
//        log.info("em={}", entityManager);  // 이렇게 호출할 경우 EntityManagerFactory에 의해 관리된다는 메세지가 출력된다.
        log.info("em={}", entityManager.unwrap(SessionImpl.class).toString());
    }
}
