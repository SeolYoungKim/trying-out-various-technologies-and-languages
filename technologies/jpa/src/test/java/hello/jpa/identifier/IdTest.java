package hello.jpa.identifier;

import hello.jpa.many_to_many.domain.Author;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class IdTest {
    @Autowired
    EntityManager entityManager;

    @Transactional
    @Test
    void idTest() {
        Author author1 = new Author("author1");
        Long idBeforePersist = author1.getId();
        assertThat(idBeforePersist).isNull();

        entityManager.persist(author1);
        Long idAfterPersist = author1.getId();
        assertThat(idAfterPersist).isNotNull();

        entityManager.flush();
        Long idAfterFlush = author1.getId();
        assertThat(idAfterFlush).isNotNull();
    }
}
