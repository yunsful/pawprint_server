package pawprint.demo.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
        @Schema(description = "반려동물 이름")
        private String petName;
        @Schema(description = "반려동물 생년월일")
        private LocalDate petBirthday;
        @Schema(description = "반려동물 성별(MALE, FEMALE)")
        private String petGender;
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
