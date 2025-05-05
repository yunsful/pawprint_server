package pawprint.demo.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pawprint.demo.apiPayload.ApiResponse;
import pawprint.demo.converter.MemberConverter;
import pawprint.demo.domain.Member;
import pawprint.demo.service.MemberService;
import pawprint.demo.web.dto.MemberRequest;
import pawprint.demo.web.dto.MemberResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {
    
    private final MemberService memberService;
    
    @PostMapping("/join")
    public ApiResponse<MemberResponse.MemberIdDto> join(@RequestBody MemberRequest.MemberJoinDto joinDto) {
        
        Member savedMember = memberService.join(joinDto);
        
        return ApiResponse.onSuccess(MemberConverter.toMemberIdDto(savedMember));
    }
    
    @PostMapping("/login")
    public ApiResponse<MemberResponse.MemberIdDto> login(@RequestBody MemberRequest.MemberLoginDto loginDto) {
        
        Member loginMember = memberService.login(loginDto);
        
        return ApiResponse.onSuccess(MemberConverter.toMemberIdDto(loginMember));
    }
    
    @PatchMapping("/update")
    public ApiResponse<MemberResponse.MemberIdDto> update(
            @RequestBody MemberRequest.MemberUpdateDto updateDto) {
        
        Member updateMember = memberService.update(updateDto);
        
        return ApiResponse.onSuccess(MemberConverter.toMemberIdDto(updateMember));
        
    }
    
    @DeleteMapping("/delete/{id}")
    public ApiResponse<MemberResponse.MemberIdDto> delete(@RequestParam Long id) {
        
        Member deletedMember = memberService.delete(id);
        
        return ApiResponse.onSuccess(MemberConverter.toMemberIdDto(deletedMember));
    }
}
