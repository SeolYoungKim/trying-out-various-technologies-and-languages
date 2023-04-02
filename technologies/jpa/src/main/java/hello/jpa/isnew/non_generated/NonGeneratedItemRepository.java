package hello.jpa.isnew.non_generated;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NonGeneratedItemRepository extends JpaRepository<NonGeneratedItem, String> {
}
