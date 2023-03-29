package hello.jpa.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductAuthorRepository extends JpaRepository<ProductAuthor, Long> {
    @Override
    @EntityGraph(attributePaths = {"product", "author"})
    Optional<ProductAuthor> findById(Long id);
}
