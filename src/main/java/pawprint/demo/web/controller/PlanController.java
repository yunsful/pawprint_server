package pawprint.demo.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pawprint.demo.apiPayload.ApiResponse;
import pawprint.demo.converter.PlanConverter;
import pawprint.demo.domain.Plan;
import pawprint.demo.service.plan.PlanService;
import pawprint.demo.web.dto.PlanRequest;
import pawprint.demo.web.dto.PlanResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/plans")
@Tag(name = "일정", description = "일정 관련 API")
@Slf4j
public class PlanController {
    
    private final PlanService planService;
    
    @PostMapping
    @Operation(summary = "일정 생성", description = "일정 정보를 입력해 일정을 생성합니다.")
    public ApiResponse<PlanResponse.PlanIdDto> createPlan(
            @Parameter(description = "일정 정보를 입력하세요")
            @RequestBody PlanRequest.PlanCreateDto request
            ) {
        Plan createPlan = planService.createPlan(request);
        return ApiResponse.onSuccess(PlanResponse.PlanIdDto.builder().id(createPlan.getId()).build());
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "일정 조회", description = "일정 id를 입력해 일정을 조회합니다.")
    public ApiResponse<PlanResponse.PlanInfoDto> getPlan(
            @Parameter(description = "일정 id를 입력하세요")
            @PathVariable Long id
    ) {
        Plan findPlan = planService.getPlanById(id);
        return ApiResponse.onSuccess(PlanResponse.PlanInfoDto.builder().id(findPlan.getId()).build());
    }
    
    @PatchMapping
    @Operation(summary = "일정 수정", description = "일정 정보를 입력해 일정을 수정합니다.")
    public ApiResponse<PlanResponse.PlanIdDto> updatePlan(
            @Parameter(description = "일정 정보를 입력하세요")
            @RequestBody PlanRequest.PlanUpdateDto request
    ) {
        Plan updatePlan = planService.updatePlan(request);
        return ApiResponse.onSuccess(PlanResponse.PlanIdDto.builder().id(updatePlan.getId()).build());
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "일정 삭제", description = "일정 id로 일정을 삭제합니다.")
    public ApiResponse<PlanResponse.PlanIdDto> deletePlan(
            @Parameter(description = "일정 id를 입력하세요")
            @PathVariable Long id
    ) {
        planService.deletePlan(id);
        return ApiResponse.onSuccess(PlanResponse.PlanIdDto.builder().id(id).build());
    }
    
    @GetMapping("/List")
    @Operation(summary = "일정 리스트 조회", description = "해당 날짜의 일정들을 조회합니다.")
    public ApiResponse<PlanResponse.PlanInfoListDto> listPlans(
            @Parameter(description = "회원 id와 날짜를 입력하세요")
            @RequestBody PlanRequest.GetPlansByDateDto request
    ) {
        List<Plan> planList = planService.getAllPlansByMemberIdAndDate(request);
        return ApiResponse.onSuccess(PlanConverter.toPlanInfoListDto(planList));
    }
    
    @PostMapping("/{id}/check")
    @Operation(summary = "일정 체트", description = "해당 일정을 체크합니다.")
    public ApiResponse<PlanResponse.PlanIdDto> checkPlan(
            @Parameter(description = "일정 id를 입력하세요")
            @PathVariable Long id
    ) {
        planService.toggleCheck(id);
        return ApiResponse.onSuccess(PlanResponse.PlanIdDto.builder().id(id).build());
    }
    
}
