package pawprint.demo.service.mission;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pawprint.demo.apiPayload.code.status.ErrorStatus;
import pawprint.demo.apiPayload.exception.handler.MemberHandler;
import pawprint.demo.apiPayload.exception.handler.MissionHandler;
import pawprint.demo.domain.Media;
import pawprint.demo.domain.Member;
import pawprint.demo.domain.Memory;
import pawprint.demo.domain.Mission;
import pawprint.demo.repository.MediaRepository;
import pawprint.demo.repository.MemberRepository;
import pawprint.demo.repository.MemoryRepository;
import pawprint.demo.repository.MissionRepository;
import pawprint.demo.service.S3Service;
import pawprint.demo.web.dto.MissionRequest;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MissionServiceImpl implements MissionService {
    
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;
    private final S3Service s3Service;
    private final MemoryRepository memoryRepository;
    private final MediaRepository mediaRepository;
    
    
    @Override
    public Mission createMission(MissionRequest.MissionCreateDto createDto) {
        
        Member findMember = memberRepository.findById(createDto.getMemberId()).orElseThrow(
                () -> new MemberHandler(ErrorStatus.Member_NOT_FOUND)
        );
        
        Mission createMission = Mission.builder()
                .member(findMember)
                .description(createDto.getDescription())
                .title(createDto.getTitle())
                .missionOrder(missionRepository.countAllByMember_Id(createDto.getMemberId()) + 1)
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
    
    @Override
    public Memory completeMission(MissionRequest.MissionCompleteDto request, List<MultipartFile> images) {
        
        Mission findMission = missionRepository.findById(request.getMissionId()).orElseThrow(
                () -> new MissionHandler(ErrorStatus.Mission_NOT_FOUND)
        );
        findMission.update(findMission.getTitle(), findMission.getDescription(), true);
        
        Member findMember = memberRepository.findById(request.getMemberId()).orElseThrow(
                () -> new MemberHandler(ErrorStatus.Member_NOT_FOUND)
        );
        
        Memory memory = Memory.builder()
                .body(request.getBody())
                .date(request.getDate())
                .count(memoryRepository.countAllByMember_Id(request.getMemberId()) + 1)
                .mission(findMission)
                .member(findMember)
                .build();
        
        memoryRepository.save(memory);
        memoryRepository.flush();
        for (MultipartFile image : images) {
            String imageAddress = s3Service.uploadFile(image);
            Media media = Media.builder()
                    .filePath(imageAddress)
                    .memory(memory)
                    .build();
            mediaRepository.save(media);
        }
        
        return memory;
    }
}
