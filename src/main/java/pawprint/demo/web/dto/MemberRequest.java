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
}
