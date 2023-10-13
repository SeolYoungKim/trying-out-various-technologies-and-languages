package hello.jpa.equality;

import hello.jpa.many_to_many.domain.Author;
import hello.jpa.many_to_many.domain.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class IdenticalAndEquivalenceTest {
    @Autowired
    private AuthorRepository authorRepository;

    @BeforeEach
    void setUp() {
        authorRepository.save(new Author("kim"));
    }

    @DisplayName("같은 영속성 컨텍스트 내에서, 같은 엔티티를 조회하면 같은 인스턴스다.")
    @Transactional
    @Test
    void test() {
        Author author1 = authorRepository.findById(1L).get();
        Author author2 = authorRepository.findById(1L).get();

        assertThat(author1).isSameAs(author2);
    }

    @DisplayName("다른 영속성 컨텍스트 내에서, 같은 엔티티를 조회하면 다른 인스턴스다.")
    @Test
    void test2() {
        // findById()는 각각 다른 트랜잭션을 개시하므로, 영속성 컨텍스트 또한 다르다.
        Author author1 = authorRepository.findById(1L).get();
        Author author2 = authorRepository.findById(1L).get();

        assertThat(author1).isNotSameAs(author2);
    }
}
