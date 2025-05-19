package pawprint.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pawprint.demo.domain.Media;

import java.util.Optional;

public interface MediaRepository extends JpaRepository<Media, Long> {
    
    Optional<Media> findFirstByMemory_Id(Long memoryId);
}
