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
import pawprint.demo.converter.MemoryConverter;
import pawprint.demo.domain.Media;
import pawprint.demo.domain.Memory;
import pawprint.demo.repository.MediaRepository;
import pawprint.demo.service.MediaService;
import pawprint.demo.service.memory.MemoryService;
import pawprint.demo.web.dto.MemoryResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/memories")
@Tag(name = "추억", description = "추억 관련 API")
@Slf4j
public class MemoryController {
    
    private final MemoryService memoryService;
    private final MediaService mediaService;
    
    @GetMapping("/members/{id}")
    @Operation(summary = "추억 리스트 조회", description = "회원 아이디로 추억 리스트를 조회합니다.")
    public ApiResponse<List<MemoryResponse.ListMemoryInfoDto>> getMemoryById(
            @Parameter(description = "회원 식별 id를 입력하세요.")
            @PathVariable Long id
    ) {
        return ApiResponse.onSuccess(memoryService.getMemoriesWithMediaByMemberId(id));
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "추억 상세 조회", description = "추억 id로 추억을 상세 조회합니다.")
    public ApiResponse<MemoryResponse.MemoryInfoDto> getMemory(
            @Parameter(description = "추억 id를 입력하세요.")
            @PathVariable Long id
    ) {
        Memory findMemory = memoryService.getMemoryById(id);
        List<Media> mediaList = mediaService.getMediaByMemoryId(findMemory.getId());
        return ApiResponse.onSuccess(MemoryConverter.toMemoryInfoDto(findMemory, mediaList));
    }
}
