package hello.jpa.equality;

import hello.jpa.many_to_many.domain.Author;
import hello.jpa.many_to_many.domain.AuthorRepository;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class IdenticalAndEquivalenceProxyTest {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private EntityManager em;

    @BeforeEach
    void setUp() {
        authorRepository.deleteAllInBatch();
    }

    @DisplayName("영속성 컨텍스트 내에서의 프록시 객체 동일성도 보장된다")
    @Transactional
    @Test
    void testProxy() {
        Author author = new Author("park");
        em.persist(author);
        em.flush();
        em.clear();

        Author reference = em.getReference(Author.class, 1L);  // 프록시로 조회
        Author find = em.find(Author.class, 1L);  // 원본 객체 조회

        System.out.println("reference = " + reference.getClass());
        System.out.println("find = " + find.getClass());

        System.out.println("프록시 초기화 수행");
        String name = find.getName();  // 프록시 초기화
        System.out.println("name = " + name);
        System.out.println("find = " + find.getClass());

        assertThat(reference).isSameAs(find);
    }
}
