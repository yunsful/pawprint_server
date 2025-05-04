package pawprint.demo.web.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pawprint.demo.apiPayload.ApiResponse;
import pawprint.demo.web.dto.MemberRequest;
import pawprint.demo.web.dto.MemberResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class MemberController {
    
    @PostMapping("/join")
    public ApiResponse<MemberResponse.MemberIdDto> join(MemberRequest.MemberJoinDto joinDto) {
        
        return ApiResponse.onSuccess(
                MemberResponse.MemberIdDto.builder()
                        .id(1L)
                        .build());
    }
}
