package pawprint.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pawprint.demo.domain.Member;
import pawprint.demo.domain.Mission;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    
    Integer countAllByMember_Id(Long memberId);
    
    List<Mission> findFirstByMemberAndIsDone(Member member, Boolean isDone);
}
