package hello.jpa.isnew.non_generated;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersistableImplRepository extends JpaRepository<PersistableImpl, String> {
}
