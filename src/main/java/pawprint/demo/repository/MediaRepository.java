package pawprint.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pawprint.demo.domain.Media;

public interface MediaRepository extends JpaRepository<Media, Long> {

}
