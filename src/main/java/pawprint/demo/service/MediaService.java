package pawprint.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pawprint.demo.domain.Media;
import pawprint.demo.repository.MediaRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MediaService {
    
    private final MediaRepository mediaRepository;
    
    public List<Media> getMediaByMemoryId(Long memoryId) {
        return mediaRepository.findAllByMemory_Id(memoryId).isEmpty() ? List.of() : mediaRepository.findAllByMemory_Id(memoryId);
    }
    
    public List<Media> getMediaByContentId(Long contentId) {
        return mediaRepository.findAllByContent_Id(contentId).isEmpty() ? List.of() : mediaRepository.findAllByContent_Id(contentId);
    }
}
