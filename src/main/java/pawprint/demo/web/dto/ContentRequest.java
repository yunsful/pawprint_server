package pawprint.demo.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

public class ContentRequest {
    
    @Getter
    public static class ContentCreateDto {
        
        @Schema(description = "회원 아이디")
        private Long memberId;
        @Schema(description = "본문")
        private String body;
    }
    
}
