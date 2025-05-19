package pawprint.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pawprint.demo.domain.Content;

public interface ContentRepository extends JpaRepository<Content, Long> {
}
