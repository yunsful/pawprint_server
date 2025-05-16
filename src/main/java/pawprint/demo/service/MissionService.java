package pawprint.demo.service;

import pawprint.demo.domain.Mission;
import pawprint.demo.web.dto.MissionRequest;

public interface MissionService {
    
    Mission createMission(MissionRequest.MissionCreateDto createDto);
    
    void deleteMission(Long id);
    
    Mission updateMission(MissionRequest.MissionUpdateDto updateDto);
    
    Mission getMission(Long id);
}
