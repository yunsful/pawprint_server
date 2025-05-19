package pawprint.demo.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pawprint.demo.apiPayload.ApiResponse;
import pawprint.demo.converter.MemberConverter;
import pawprint.demo.domain.Member;
import pawprint.demo.service.member.MemberService;
import pawprint.demo.web.dto.MemberRequest;
import pawprint.demo.web.dto.MemberResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
@Tag(name = "회원", description = "회원 관련 API")
@Slf4j
public class MemberController {
    
    private final MemberService memberService;
    
    @PostMapping(value = "/join", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "회원가입", description = "회원 정보를 입력해 회원가입합니다.")
    public ApiResponse<MemberResponse.MemberIdDto> join(
            @RequestPart(value = "profileImage", required = false)
            @Parameter(description = "프로필 이미지") MultipartFile profileImage,
            @RequestPart(value = "joinDto")
            MemberRequest.MemberJoinDto joinDto) {
        
        log.info("서비스 호출 전");
        Member savedMember = memberService.join(profileImage, joinDto);
        log.info("서비스 호출 후");
        
        return ApiResponse.onSuccess(MemberConverter.toMemberIdDto(savedMember));
    }
    
    @PostMapping("/login")
    @Operation(summary = "로그인", description = "아이디와 비밀번호를 입력하면 회원 식별 id가 반환됩니다.")
    public ApiResponse<MemberResponse.MemberIdDto> login(
            @Parameter(description = "아이디, 비밀번호를 입력하세요.")
            @RequestBody MemberRequest.MemberLoginDto loginDto) {
        
        Member loginMember = memberService.login(loginDto);
        
        return ApiResponse.onSuccess(MemberConverter.toMemberIdDto(loginMember));
    }
    
    @PatchMapping("/")
    @Operation(summary = "회원정보 수정", description = "회원정보를 수정합니다.")
    public ApiResponse<MemberResponse.MemberIdDto> update(
            @Parameter(description = "닉네임, 상태 메시지를 입력하세요.")
            @RequestBody MemberRequest.MemberUpdateDto updateDto) {
        
        Member updateMember = memberService.update(updateDto);
        
        return ApiResponse.onSuccess(MemberConverter.toMemberIdDto(updateMember));
        
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "회원삭제(탈퇴)", description = "회원을 삭제(탈퇴)합니다.")
    public ApiResponse<MemberResponse.MemberIdDto> delete(
            @Parameter(description = "회원 식별 id를 입력하세요.")
            @PathVariable Long id) {
        
        Member deletedMember = memberService.delete(id);
        
        return ApiResponse.onSuccess(MemberConverter.toMemberIdDto(deletedMember));
    }
}
