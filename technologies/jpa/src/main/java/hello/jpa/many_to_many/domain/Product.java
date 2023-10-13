package hello.jpa.many_to_many.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.Getter;

@Getter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    // 아래의 연관관계를 이용해도 N+1 문제가 동일하게 발생함
    // 따라서, 다른 방법을 고려해야 할 것 같음
    // 1. QeuryDSL을 사용
    // 2. @EntityGraph를 사용 -> X
    // 3. @BatchSize를 사용
    // 4. @Query를 사용 -> O
    @OneToMany(mappedBy = "product")
    private List<ProductAuthor> productAuthors = new ArrayList<>();

    protected Product() {
    }

    public Product(Long id) {
        this.id = id;
    }

    public Product(final String name, final String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;

        return Objects.equals(id, product.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // ManyToMany 관련 코드
    // 따로 테이블을 지정하고 사용하지 않을 경우, 즉, JPA가 제공하는 ManyToMany를 그대로 사용할 경우, N+1 문제가 발생함.. 쿼리 예측 불가 ㅠ
    // ManyToMany는 기본적으로 중간 테이블을 생성하고, 그 테이블을 통해 연결함
    // 실무에서는 사용하지 말자......
//    @ManyToMany
//    @JoinTable(name = "product_author")
//    private List<Author> authors = new ArrayList<>();

//    public void addAuthor(Author author) {
//        authors.add(author);
//    }
}
