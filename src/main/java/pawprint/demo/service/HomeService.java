package pawprint.demo.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pawprint.demo.apiPayload.code.status.ErrorStatus;
import pawprint.demo.apiPayload.exception.handler.MemberHandler;
import pawprint.demo.domain.Member;
import pawprint.demo.domain.Mission;
import pawprint.demo.domain.Pet;
import pawprint.demo.repository.MemberRepository;
import pawprint.demo.repository.MemoryRepository;
import pawprint.demo.repository.MissionRepository;
import pawprint.demo.repository.PetRepository;
import pawprint.demo.service.memory.MemoryService;
import pawprint.demo.web.dto.HomeResponse;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class HomeService {
    
    private final MemberRepository memberRepository;
    private final PetRepository petRepository;
    private final MissionRepository missionRepository;
    private final MemoryRepository memoryRepository;
    
    public HomeResponse.HomeBannerDto getBannerInfo(Long memberId) {
        
        Member findMember = memberRepository.findById(memberId).orElseThrow(
                () -> new MemberHandler(ErrorStatus.Member_NOT_FOUND)
        );
        
        List<Pet> findFirstPet = petRepository.findFirstByMember(findMember).isEmpty() ? List.of() : petRepository.findFirstByMember(findMember);
        
        Integer memoryCount = memoryRepository.countAllByMember_Id(memberId);
        
        return HomeResponse.HomeBannerDto.builder()
                .name(findMember.getName())
                .pName(findFirstPet.get(0).getName())
                .memoryCount(memoryCount)
                .build();
    }
    
    public Mission getTodayMission(Long memberId) {
        
        Member findMember = memberRepository.findById(memberId).orElseThrow(
                () -> new MemberHandler(ErrorStatus.Member_NOT_FOUND)
        );
        
        List<Mission> firstByMemberAndIsDone = missionRepository.findFirstByMemberAndIsDone(findMember, false);
        
        return firstByMemberAndIsDone.isEmpty() ? null : firstByMemberAndIsDone.get(0);
    }
}
