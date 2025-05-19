package pawprint.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pawprint.demo.domain.Memory;

import java.util.List;

public interface MemoryRepository extends JpaRepository<Memory, Long> {
    
    Integer countAllByMember_Id(Long memberId);
    
    List<Memory> findAllByMember_Id(Long memberId);
}
