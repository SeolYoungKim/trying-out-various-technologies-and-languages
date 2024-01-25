package hello.jpa.jointest;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerJpaRepository extends JpaRepository<Banner, Long> {
}
