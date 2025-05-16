package pawprint.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pawprint.demo.domain.Memory;

public interface MemoryRepository extends JpaRepository<Memory, Long> {
    
    Integer countAllByMember_Id(Long memberId);
}
