package pawprint.demo.service;

import org.springframework.web.multipart.MultipartFile;
import pawprint.demo.domain.Member;
import pawprint.demo.web.dto.MemberRequest;

public interface MemberService {
    
    Member join(MultipartFile profileImage, MemberRequest.MemberJoinDto joinDto);
    
    Member login(MemberRequest.MemberLoginDto loginDto);
    
    Member update(MemberRequest.MemberUpdateDto updateDto);
    
    Member delete(Long id);
}
