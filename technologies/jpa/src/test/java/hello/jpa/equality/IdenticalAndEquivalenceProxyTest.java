package hello.jpa.equality;

import hello.jpa.many_to_many.domain.Author;
import hello.jpa.many_to_many.domain.AuthorRepository;
import hello.jpa.many_to_many.domain.Product;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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

    @DisplayName("프록시는 instanceof로 비교해야 한다.")
    @Transactional
    @Test
    void testProxy2() {
        Author author = new Author("park");
        em.persist(author);
        em.flush();
        em.clear();

        Author proxy = em.getReference(Author.class, 1L);
        assertThat(proxy.getClass()).isNotSameAs(Author.class);
        assertThat(proxy).isInstanceOf(Author.class);
    }

    @DisplayName("Class를 동일 비교(==)하는 equals()를 사용하면 proxy와 author가 같은 id를 가짐에도 불구하고 false를 반환한다.")
    @Transactional
    @Test
    void testProxy3() {
        Author author = new Author("park");
        em.persist(author);
        em.flush();
        em.clear();

        Author newAuthor = new Author(1L);
        Author proxy = em.getReference(Author.class, 1L);

        // 원래는 id값이 동일하므로, newAuthor와 proxy는 동등해야 한다.
        assertThat(proxy).isNotEqualTo(newAuthor);  // 하지만 equals() 내부에서 instanceof가 아닌, class의 동일성(==)을 비교하기 때문에 equals()가 false를 반환한다.
    }

    @DisplayName("instanceof를 이용한 equals()를 사용하면 proxy와 author가 같은 id를 가지면 true를 반환한다.")
    @Transactional
    @Test
    void testProxy4() {
        Product product = new Product("name", "desc");
        em.persist(product);
        em.flush();
        em.clear();

        Product newProduct = new Product(1L);
        Product proxy = em.getReference(Product.class, 1L);

        assertThat(proxy).isEqualTo(newProduct);
    }
}
