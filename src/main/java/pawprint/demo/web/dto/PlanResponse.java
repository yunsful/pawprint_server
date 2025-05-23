package pawprint.demo.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class PlanResponse {
    
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    @Schema(description = "일정 id 반환 DTO")
    public static class PlanIdDto {
        
        private Long id;
    }
    
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    @Schema(description = "일정 정보 반환 DTO")
    public static class PlanInfoDto {
        
        private Long id;
        private String title;
        private LocalDate date;
        private LocalTime time;
        private Boolean isChecked;
    }
    
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    @Schema(description = "일정 정보 리스트 반환 DTO")
    public static class PlanInfoListDto {
        
        private List<PlanInfoDto> plans;
    }
}
