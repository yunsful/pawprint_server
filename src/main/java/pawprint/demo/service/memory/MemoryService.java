package pawprint.demo.service.memory;

import pawprint.demo.web.dto.MemoryResponse;

import java.util.List;

public interface MemoryService {
    
    List<MemoryResponse.MemoryInfoDto> getMemoriesWithMediaByMemberId(Long memberId);
}
