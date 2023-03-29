package hello.jpa.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {
//    @Override
//    @EntityGraph(attributePaths = {"productAuthors"})  // 여전히 N+1 문제 발생. author까지는 fetch join을 해주지 않음
//    Optional<Product> findById(Long id);

    @Query("select p from Product p join fetch p.productAuthors pa join fetch pa.author where p.id = :id")
    Optional<Product> findByIdFetchJoin(@Param("id") Long id);

//    @Modifying  // 이걸 붙여야 update 쿼리가 실행됨
//    @Query("update Product p set p.name = :name where p.id = :id")
//    void updateName(@Param("id") Long id, @Param("name") String name);

    // 벌크 연산 조심
    // 벌크 연산은 영속성 컨텍스트를 무시하고 DB에 직접 쿼리를 날리기 때문에, 영속성 컨텍스트에 있는 데이터와 DB에 있는 데이터가 달라질 수 있음
    // 따라서, 벌크 연산을 수행한 후에는 영속성 컨텍스트를 초기화해야 함
    // 이를 위해, @Modifying(clearAutomatically = true)를 사용하면 됨
    @Modifying(clearAutomatically = true)  // 영속성 컨텍스트와 DB를 동기화 시킴 (flush, clear 없이 가능)
    @Query("update Product p set p.name = :name where p.id = :id")
    void updateName(@Param("id") Long id, @Param("name") String name);
}
