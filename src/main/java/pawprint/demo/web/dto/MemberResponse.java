package pawprint.demo.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

public class MemberResponse {
    
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    @Schema(description = "회원 id 반환 DTO")
    public static class MemberIdDto {
        
        private Long id;
    }
    
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    @Schema(description = "회원 정보 반환 DTO")
    public static class MemberInfoDto {
        
        private String name;
        private String statusNote;
        private String profile;
        
    }
}
