package pawprint.demo.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

public class ContentResponse {
    
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    @Schema(description = "게시글 id 반환 DTO")
    public static class ContentIdDto {
        
        private Long id;
    }
}
