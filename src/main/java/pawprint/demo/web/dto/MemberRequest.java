package pawprint.demo.web.dto;

import lombok.Getter;

public class MemberRequest {
    
    @Getter
    public static class MemberJoinDto {
        
        private String userId;
        private String password;
        private String name;
        private String statusNote;
    }
    
    @Getter
    public static class MemberLoginDto {
        
        private String userId;
        private String password;
    }
    
    @Getter
    public static class MemberUpdateDto {
        
        private Long id;
        private String name;
        private String statusNote;
    }
}
