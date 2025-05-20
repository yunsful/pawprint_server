package pawprint.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pawprint.demo.domain.Content;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Long> {
    List<Content> findByMember_Id(Long memberId);
}
