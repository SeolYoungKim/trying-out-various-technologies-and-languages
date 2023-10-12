package hello.jpa.entitymanager;

import jakarta.persistence.EntityManager;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.internal.SessionImpl;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Getter
@Repository
public class TestRepository {
    private final EntityManager entityManager;
    private final Set<EntityManager> calledEntityManagerSet = new HashSet<>();

    public TestRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void printEntityManager() {
//        log.info("em={}", entityManager);  // 이렇게 호출할 경우 EntityManagerFactory에 의해 관리된다는 메세지가 출력된다.
        log.info("[TestRepository] entityManager={}", entityManager.unwrap(SessionImpl.class).toString());
        calledEntityManagerSet.add(entityManager.unwrap(SessionImpl.class));
    }
}
