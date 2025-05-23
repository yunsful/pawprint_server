package pawprint.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pawprint.demo.domain.Plan;

import java.time.LocalDate;
import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Long> {
    List<Plan> getPlansByMember_IdAndDate(Long memberId, LocalDate date);
}
