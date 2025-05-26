package pawprint.demo.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

public class HomeResponse {
    
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    @Schema(description = "홈화면 배너 정보 반환 DTO")
    public static class HomeBannerDto {
    
        private String name;
        private String pName;
        private Integer memoryCount;
    }
}
