package pawprint.demo.service.plan;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pawprint.demo.apiPayload.code.status.ErrorStatus;
import pawprint.demo.apiPayload.exception.handler.MemberHandler;
import pawprint.demo.apiPayload.exception.handler.PlanHandler;
import pawprint.demo.domain.Member;
import pawprint.demo.domain.Plan;
import pawprint.demo.repository.MemberRepository;
import pawprint.demo.repository.PlanRepository;
import pawprint.demo.web.dto.PlanRequest;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {
    
    private final PlanRepository planRepository;
    private final MemberRepository memberRepository;
    
    
    @Override
    public Plan createPlan(PlanRequest.PlanCreateDto request) {
        
        Member findMember = memberRepository.findById(request.getMemberId()).orElseThrow(
                () -> new MemberHandler(ErrorStatus.Member_NOT_FOUND)
        );
        
        return planRepository.save(Plan.builder()
                .member(findMember)
                .date(request.getDate())
                .time(request.getTime())
                .title(request.getTitle())
                .isChecked(false)
                .build());
    }
    
    @Override
    public Plan getPlanById(Long id) {
        
        return planRepository.findById(id).orElseThrow(
                () -> new PlanHandler(ErrorStatus.PLAN_NOT_FOUND)
        );
    }
    
    @Override
    public Plan updatePlan(PlanRequest.PlanUpdateDto request) {
        
        Member findMember = memberRepository.findById(request.getMemberId()).orElseThrow(
                () -> new MemberHandler(ErrorStatus.Member_NOT_FOUND)
        );
        
        Plan findPlan = planRepository.findById(request.getPlanId()).orElseThrow(
                () -> new PlanHandler(ErrorStatus.PLAN_NOT_FOUND)
        );
        
        if (!findPlan.getMember().getId().equals(findMember.getId())) {
            throw new PlanHandler(ErrorStatus._UNAUTHORIZED);
        }
        
        findPlan.update(request.getTitle(), request.getDate(), request.getTime());
        
        return findPlan;
    }
    
    @Override
    public void deletePlan(Long id) {
        Plan findPlan = planRepository.findById(id).orElseThrow(
                () -> new PlanHandler(ErrorStatus.PLAN_NOT_FOUND)
        );
        
        planRepository.delete(findPlan);
    }
    
    @Override
    public List<Plan> getAllPlansByMemberIdAndDate(PlanRequest.PlanListByDateDTO request) {
        
        return planRepository.getPlansByMember_IdAndDate(request.getMemberId(), request.getDate())
                .isEmpty() ? List.of() : planRepository.getPlansByMember_IdAndDate(request.getMemberId(), request.getDate());
    }
    
    @Override
    public void toggleCheck(Long id) {
        
        Plan findPlan = planRepository.findById(id).orElseThrow(
                () -> new PlanHandler(ErrorStatus.PLAN_NOT_FOUND)
        );
        Boolean before = findPlan.getIsChecked();
        findPlan.check();
        planRepository.flush();
        
        Plan checkPlan = planRepository.findById(id).orElseThrow(
                () -> new PlanHandler(ErrorStatus.PLAN_NOT_FOUND)
        );
        if (before == checkPlan.getIsChecked()) {
            throw new PlanHandler(ErrorStatus._INTERNAL_SERVER_ERROR);
        }
    }
}
