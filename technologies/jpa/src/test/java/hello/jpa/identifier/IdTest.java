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
        Author author = new Author("author1");
        Long idBeforePersist = author.getId();
        assertThat(idBeforePersist).isNull();

        entityManager.persist(author);
        Long idAfterPersist = author.getId();
        assertThat(idAfterPersist).isNotNull();

        /*
         * 영속 상태에서 조회
         */
        Author findedAuthor = entityManager.find(Author.class, 1);
        assertThat(author.getId()).isEqualTo(findedAuthor.getId());
        assertThat(author).isSameAs(findedAuthor);

        entityManager.flush();
        Long idAfterFlush = author.getId();
        assertThat(idAfterFlush).isNotNull();

        /*
         * 준영속 상태로 만든 후 다시 조회
         */
        // 위의 author들을 준영속 상태로 만든 후
        entityManager.clear();

        // 다시 같은 author를 찾으면
        Author findedAuthor2 = entityManager.find(Author.class, 1);

        // 동일하지 않다. -> 이는 주의해야 할 사항이다. 영속성 컨텍스트 내에서 조회한 같은 엔티티들만 동일성이 보장된다.
        assertThat(author).isNotSameAs(findedAuthor2);
    }
}
