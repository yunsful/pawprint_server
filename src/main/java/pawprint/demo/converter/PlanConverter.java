package pawprint.demo.converter;

import pawprint.demo.domain.Plan;
import pawprint.demo.web.dto.PlanResponse;

import java.util.List;

public class PlanConverter {
    
    public static PlanResponse.PlanInfoDto toPlanInfoDto(Plan plan) {
        
        return PlanResponse.PlanInfoDto.builder()
                .id(plan.getId())
                .title(plan.getTitle())
                .date(plan.getDate())
                .time(plan.getTime())
                .isChecked(plan.getIsChecked())
                .build();
    }
    
    public static PlanResponse.PlanInfoListDto toPlanInfoListDto(List<Plan> planList) {
        
        return PlanResponse.PlanInfoListDto.builder()
                .plans(planList.stream().map(PlanConverter::toPlanInfoDto).toList())
                .build();
    }
}
