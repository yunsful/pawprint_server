package pawprint.demo.converter;

import pawprint.demo.domain.Mission;
import pawprint.demo.web.dto.MissionResponse;

import java.security.PublicKey;

public class MissionConverter {
    
    public static MissionResponse.MissionIdDto toMissionIdDto(Mission mission) {
        return MissionResponse.MissionIdDto.builder()
                .id(mission.getId())
                .build();
    }
    
    public static MissionResponse.MissionInfoDto toMissionInfoDto(Mission mission) {
        return MissionResponse.MissionInfoDto.builder()
                .id(mission.getId())
                .title(mission.getTitle())
                .description(mission.getDescription())
                .order(mission.getOrder())
                .isDone(mission.getIsDone())
                .build();
    }
}
