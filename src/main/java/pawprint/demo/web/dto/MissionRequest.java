package pawprint.demo.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDate;

public class MissionRequest {
    
    @Getter
    public static class MissionCreateDto {
        
        @Schema(description = "회원식별 id")
        private Long memberId;
        @Schema(description = "미션 제목")
        private String title;
        @Schema(description = "미션 설명")
        private String description;
    }
    
    @Getter
    public static class MissionUpdateDto {
        
        @Schema(description = "미션 id")
        private Long id;
        @Schema(description = "미션 제목")
        private String title;
        @Schema(description = "미션 설명")
        private String description;
    }
    
    @Getter
    public static class MissionCompleteDto {
        
        @Schema(description = "미션 id")
        private Long missionId;
        @Schema(description = "회원 id")
        private Long memberId;
        @Schema(description = "미션 내용")
        private String body;
        @Schema(description = "완료 날짜")
        private LocalDate date;
    }
    
}
