package pawprint.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pawprint.demo.domain.Content;
import pawprint.demo.domain.Media;

import java.util.List;
import java.util.Optional;

public interface MediaRepository extends JpaRepository<Media, Long> {
    
    Optional<Media> findFirstByMemory_Id(Long memoryId);
    
    List<Media> findAllByMemory_Id(Long id);
    
    List<Media> findAllByContent(Content content);
    
    List<Media> findAllByContent_Id(Long contentId);
}
