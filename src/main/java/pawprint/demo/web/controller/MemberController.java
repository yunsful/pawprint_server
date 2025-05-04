package pawprint.demo.web.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pawprint.demo.apiPayload.ApiResponse;
import pawprint.demo.domain.Member;
import pawprint.demo.service.MemberService;
import pawprint.demo.web.dto.MemberRequest;
import pawprint.demo.web.dto.MemberResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class MemberController {
    
    private final MemberService memberService;
    
    @PostMapping("/join")
    public ApiResponse<MemberResponse.MemberIdDto> join(MemberRequest.MemberJoinDto joinDto) {
        
        Member savedMember = memberService.join(joinDto);
        
        return ApiResponse.onSuccess(
                MemberResponse.MemberIdDto.builder()
                        .id(savedMember.getId())
                        .build());
    }
}
