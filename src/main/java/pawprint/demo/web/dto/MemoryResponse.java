package pawprint.demo.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

public class MemoryResponse {
    
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    @Schema(description = "추억 id 반환 DTO")
    public static class MemoryIdDto {
        
        private Long id;
    }
}
