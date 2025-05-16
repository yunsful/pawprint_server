package pawprint.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pawprint.demo.apiPayload.code.status.ErrorStatus;
import pawprint.demo.apiPayload.exception.handler.MemberHandler;
import pawprint.demo.apiPayload.exception.handler.MissionHandler;
import pawprint.demo.domain.Member;
import pawprint.demo.domain.Mission;
import pawprint.demo.repository.MemberRepository;
import pawprint.demo.repository.MissionRepository;
import pawprint.demo.web.dto.MissionRequest;

@Service
@Transactional
@RequiredArgsConstructor
public class MissionServiceImpl implements MissionService {
    
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;
    
    
    @Override
    public Mission createMission(MissionRequest.MissionCreateDto createDto) {
        
        Member findMember = memberRepository.findById(createDto.getMemberId()).orElseThrow(
                () -> new MemberHandler(ErrorStatus.Member_NOT_FOUND)
        );
        
        Mission createMission = Mission.builder()
                .member(findMember)
                .description(createDto.getDescription())
                .title(createDto.getTitle())
                .order(missionRepository.countAllByMember_Id(createDto.getMemberId()) + 1)
                .isDone(false)
                .build();
        
        return missionRepository.save(createMission);
    }
    
    @Override
    public void deleteMission(Long id) {
        
        Mission findMission = missionRepository.findById(id).orElseThrow(
                () -> new MissionHandler(ErrorStatus.Mission_NOT_FOUND)
        );
        
        missionRepository.delete(findMission);
        missionRepository.flush();
        
        missionRepository.findById(id).ifPresent(mission -> {
            throw new MissionHandler(ErrorStatus._INTERNAL_SERVER_ERROR);
        });
    }
    
    @Override
    public Mission updateMission(MissionRequest.MissionUpdateDto updateDto) {
        Mission findMission = missionRepository.findById(updateDto.getId()).orElseThrow(
                () -> new MissionHandler(ErrorStatus.Mission_NOT_FOUND)
        );
        
        findMission.update(updateDto.getTitle(), updateDto.getDescription(), findMission.getIsDone());
        return findMission;
    }
    
    @Override
    public Mission getMission(Long id) {
        return missionRepository.findById(id).orElseThrow(
                () -> new MissionHandler(ErrorStatus.Mission_NOT_FOUND)
        );
    }
}
