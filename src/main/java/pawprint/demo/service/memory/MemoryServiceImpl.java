package pawprint.demo.service.memory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pawprint.demo.apiPayload.code.status.ErrorStatus;
import pawprint.demo.apiPayload.exception.handler.MemoryHandler;
import pawprint.demo.domain.Media;
import pawprint.demo.domain.Memory;
import pawprint.demo.repository.MediaRepository;
import pawprint.demo.repository.MemoryRepository;
import pawprint.demo.web.dto.MemoryResponse;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemoryServiceImpl implements MemoryService{

    private final MemoryRepository memoryRepository;
    private final MediaRepository mediaRepository;
    
    @Override
    public Memory getMemoryById(Long id) {
        
        return memoryRepository.findById(id).orElseThrow(
                () -> new MemoryHandler(ErrorStatus.MEMORY_NOT_FOUND)
        );
    }
    
    @Override
    public List<MemoryResponse.ListMemoryInfoDto> getMemoriesWithMediaByMemberId(Long memberId) {
        
        List<Memory> memories = memoryRepository.findAllByMember_Id(memberId);
        
        return memories.stream()
                .map(memory -> {
                    String mediaPath = mediaRepository.findFirstByMemory_Id(memory.getId())
                            .map(Media::getFilePath)
                            .orElse(null);
                    
                    return MemoryResponse.ListMemoryInfoDto.builder()
                            .id(memory.getId())
                            .body(memory.getBody())
                            .date(memory.getDate().toString())
                            .count(memory.getCount())
                            .media(mediaPath)
                            .build();
                })
                .toList();
    }
}
