package pawprint.demo.service.memory;

import pawprint.demo.domain.Memory;
import pawprint.demo.web.dto.MemoryResponse;

import java.util.List;

public interface MemoryService {
    
    List<MemoryResponse.ListMemoryInfoDto> getMemoriesWithMediaByMemberId(Long memberId);
    
    Memory getMemoryById(Long id);
}
