package pawprint.demo.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

public class PlanRequest {
    
    @Getter
    public static class PlanCreateDto {
        
        @Schema(description = "회원식별 id")
        private Long memberId;
        @Schema(description = "일정 제목")
        private String title;
        @Schema(description = "일정 날짜")
        private LocalDate date;
        @Schema(description = "일정 시간")
        private LocalTime time;
    }
    
    @Getter
    public static class PlanUpdateDto {
        
        @Schema(description = "회원식별 id")
        private Long memberId;
        @Schema(description = "일정 id")
        private Long planId;
        @Schema(description = "일정 제목")
        private String title;
        @Schema(description = "일정 날짜")
        private LocalDate date;
        @Schema(description = "일정 시간")
        private LocalTime time;
    }
    
    @Getter
    public static class GetPlansByDateDto {
        
        @Schema(description = "회원식별 id")
        private Long memberId;
        @Schema(description = "일정 날짜")
        private LocalDate date;
    }
}
