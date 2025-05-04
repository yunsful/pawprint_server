package pawprint.demo.service;

import pawprint.demo.domain.Member;
import pawprint.demo.web.dto.MemberRequest;

public interface MemberService {
    
    Member join(MemberRequest.MemberJoinDto joinDto);
    
    Member login(MemberRequest.MemberLoginDto loginDto);
}
