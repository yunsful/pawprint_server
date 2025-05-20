package pawprint.demo.service.content;

import org.springframework.web.multipart.MultipartFile;
import pawprint.demo.domain.Content;
import pawprint.demo.web.dto.ContentRequest;

import java.util.List;

public interface ContentService {
    
    Content save(ContentRequest.ContentCreateDto request, List<MultipartFile> files);
    
    Content update(ContentRequest.ContentUpdateDto request, List<MultipartFile> files);
    
    Content getById(Long id);
    
    void deleteById(Long id);
    
    List<Content> getAllByMemberId(Long memberId);
}
