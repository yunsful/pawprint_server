package pawprint.demo.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pawprint.demo.apiPayload.ApiResponse;
import pawprint.demo.domain.Memory;
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
    
    @GetMapping("/{id}")
    public ApiResponse<List<MemoryResponse.MemoryInfoDto>> getMemoryById(
            @PathVariable Long id
    ) {
        return ApiResponse.onSuccess(memoryService.getMemoriesWithMediaByMemberId(id));
    }
}
