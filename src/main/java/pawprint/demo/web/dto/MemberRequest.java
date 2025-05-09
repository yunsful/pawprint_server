package pawprint.demo.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

public class MemberRequest {
    
    @Getter
    public static class MemberJoinDto {
        
        @Schema(description = "아이디")
        private String userId;
        @Schema(description = "비밀번호")
        private String password;
        @Schema(description = "닉네임")
        private String name;
        @Schema(description = "상태 메세지")
        private String statusNote;
    }
    
    @Getter
    public static class MemberLoginDto {
        @Schema(description = "아이디")
        private String userId;
        @Schema(description = "비밀번호")
        private String password;
    }
    
    @Getter
    public static class MemberUpdateDto {
        
        @Schema(description = "사용자 식별 id")
        private Long id;
        @Schema(description = "닉네임")
        private String name;
        @Schema(description = "상태 메시지")
        private String statusNote;
    }
}
