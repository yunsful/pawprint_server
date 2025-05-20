package pawprint.demo.converter;

import pawprint.demo.domain.Media;
import pawprint.demo.domain.Memory;
import pawprint.demo.web.dto.MemoryResponse;

import java.util.List;

public class MemoryConverter {
    
    public static MemoryResponse.MemoryInfoDto toMemoryInfoDto(Memory memory, List<Media> mediaList) {
        
        if (mediaList == null) return MemoryResponse.MemoryInfoDto.builder()
                .id(memory.getId())
                .body(memory.getBody())
                .count(memory.getCount())
                .date(memory.getDate())
                .images(List.of())
                .build();
        List<String> images = mediaList.stream().map(Media::getFilePath).toList();
        
        return MemoryResponse.MemoryInfoDto.builder()
                .id(memory.getId())
                .body(memory.getBody())
                .count(memory.getCount())
                .date(memory.getDate())
                .images(images)
                .build();
    }
}
