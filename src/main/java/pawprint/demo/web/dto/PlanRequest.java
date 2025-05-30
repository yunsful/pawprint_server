package pawprint.demo.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    @NoArgsConstructor
    public static class PlanListByDateDTO {
        
        @Schema(description = "회원식별 id")
        private Long memberId;
        @Schema(description = "일정 날짜")
        @JsonFormat(pattern = "yyyy-MM-dd") // Jackson이 LocalDate를 파싱할 수 있도록 명시
        private LocalDate date;
    }
}
