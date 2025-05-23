package pawprint.demo.service.plan;

import pawprint.demo.domain.Plan;
import pawprint.demo.web.dto.PlanRequest;

import java.util.List;

public interface PlanService {
    
    Plan createPlan(PlanRequest.PlanCreateDto request);
    
    Plan getPlanById(Long id);
    
    Plan updatePlan(PlanRequest.PlanUpdateDto request);
    
    void deletePlan(Long id);
    
    List<Plan> getAllPlansByMemberIdAndDate(PlanRequest.GetPlansByDateDto request);
    
    void toggleCheck(Long id);
}
