package pawprint.demo.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pawprint.demo.apiPayload.ApiResponse;
import pawprint.demo.converter.MissionConverter;
import pawprint.demo.domain.Mission;
import pawprint.demo.service.MissionService;
import pawprint.demo.web.dto.MissionRequest;
import pawprint.demo.web.dto.MissionResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
@Tag(name = "미션", description = "미션 관련 API")
@Slf4j
public class MissionController {
    
    private final MissionService missionService;

    @PostMapping
    @Operation(summary = "미션 생성", description = "미션 정보를 입력해 미션을 생성합니다.")
    public ApiResponse<MissionResponse.MissionIdDto> createMission(
            @Parameter(description = "회원id 및 미션 정보를 입력하세요")
            @RequestBody MissionRequest.MissionCreateDto request
            ) {
        
        Mission mission = missionService.createMission(request);
        
        return ApiResponse.onSuccess(MissionConverter.toMissionIdDto(mission));
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "미션 삭제", description = "미션 id로 미션을 삭제합니다.")
    public ApiResponse<MissionResponse.MissionIdDto> deleteMission(
            @Parameter(description = "미션 id를 입력하세요")
            @PathVariable Long id
    ) {
        missionService.deleteMission(id);
        return ApiResponse.onSuccess(MissionResponse.MissionIdDto.builder().id(id).build());
    }
    
    @PatchMapping
    @Operation(summary = "미션 수정", description = "미션을 수정합니다.")
    public ApiResponse<MissionResponse.MissionIdDto> updateMission(
            @Parameter(description = "업데이트 할 미션 id 및 미션 정보를 입력하세요.")
            @RequestBody MissionRequest.MissionUpdateDto request
    ) {
        Mission mission = missionService.updateMission(request);
        
        return ApiResponse.onSuccess(MissionConverter.toMissionIdDto(mission));
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "미션 조회", description = "미션 id로 미션을 조회합니다.")
    public ApiResponse<MissionResponse.MissionInfoDto> getMission(
            @Parameter(description = "조회할 미션 id를 입력하세요.")
            @PathVariable Long id
    ) {
        Mission mission = missionService.getMission(id);
        
        return ApiResponse.onSuccess(MissionConverter.toMissionInfoDto(mission));
    }
    
    @PostMapping("/complete")
    public ApiResponse<MissionResponse.MissionIdDto> completeMission(
            @RequestPart MissionRequest.MissionCompleteDto request,
            @RequestPart List<MultipartFile> images
    ) {
        missionService.completeMission(request, images);
        
        return ApiResponse.onSuccess(MissionResponse.MissionIdDto.builder().id(request.getMissionId()).build());
    }
}
