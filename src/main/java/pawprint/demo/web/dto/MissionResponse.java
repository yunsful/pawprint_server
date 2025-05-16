package pawprint.demo.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

public class MissionResponse {
    
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    @Schema(description = "미션 id 반환 DTO")
    public static class MissionIdDto {
        
        private Long id;
    }
    
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    @Schema(description = "미션 정보 반환 DTO")
    public static class MissionInfoDto {
        
        private Long id;
        private String title;
        private String description;
        private Boolean isDone;
        private Integer order;
    }
}
