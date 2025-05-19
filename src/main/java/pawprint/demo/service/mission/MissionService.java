package pawprint.demo.service;

import org.springframework.web.multipart.MultipartFile;
import pawprint.demo.domain.Memory;
import pawprint.demo.domain.Mission;
import pawprint.demo.web.dto.MissionRequest;

import java.util.List;

public interface MissionService {
    
    Mission createMission(MissionRequest.MissionCreateDto createDto);
    
    void deleteMission(Long id);
    
    Mission updateMission(MissionRequest.MissionUpdateDto updateDto);
    
    Mission getMission(Long id);
    
    Memory completeMission(MissionRequest.MissionCompleteDto request, List<MultipartFile> images);
}
