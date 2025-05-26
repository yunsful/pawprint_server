package pawprint.demo.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pawprint.demo.apiPayload.ApiResponse;
import pawprint.demo.apiPayload.code.status.ErrorStatus;
import pawprint.demo.apiPayload.exception.handler.MissionHandler;
import pawprint.demo.domain.Mission;
import pawprint.demo.service.HomeService;
import pawprint.demo.web.dto.HomeResponse;
import pawprint.demo.web.dto.MissionResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/home")
@Tag(name = "홈", description = "홈 화면 관련 API")
@Slf4j
public class HomeController {
    
    private final HomeService homeService;
    
    @GetMapping("/{id}")
    @Operation(summary = "배너 정보", description = "홈화면 배너 정보를 조회합니다.")
    public ApiResponse<HomeResponse.HomeBannerDto> getBannerInfo(
            @Parameter(description = "회원 id를 입력하세요")
            @PathVariable Long id
    ) {
        return ApiResponse.onSuccess(homeService.getBannerInfo(id));
    }
    
    @GetMapping("/{id}/missions")
    @Operation(summary = "오늘의 미션", description = "오늘의 미션을 조회합니다.")
    public ApiResponse<MissionResponse.MissionInfoDto> getTodayMission(
            @PathVariable Long id
    ) {
        Mission mission = homeService.getTodayMission(id);
        if (mission == null) {
            return ApiResponse.onSuccess(MissionResponse.MissionInfoDto.builder().build());
        }
        return ApiResponse.onSuccess(MissionResponse.MissionInfoDto.builder()
                .id(mission.getId())
                .title(mission.getTitle())
                .order(mission.getMissionOrder())
                .isDone(mission.getIsDone())
                .description(mission.getDescription())
                .build());
    }
    
    
}
