package pawprint.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pawprint.demo.domain.Mission;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    
    Integer countAllByMember_Id(Long memberId);

}
