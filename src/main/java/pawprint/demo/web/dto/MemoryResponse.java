package pawprint.demo.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

public class MemoryResponse {
    
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    @Schema(description = "추억 id 반환 DTO")
    public static class MemoryIdDto {
        
        private Long id;
    }
    
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    @Schema(description = "리스트 추억 정보 반환 DTO")
    public static class ListMemoryInfoDto {
        private Long id;
        private String body;
        private String date;
        private Integer count;
        private String media;
    }
    
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    @Schema(description = "추억 정보 반환 DTO")
    public static class MemoryInfoDto {
        private Long id;
        private String body;
        private LocalDate date;
        private Integer count;
        private List<String> images;
    }
}
