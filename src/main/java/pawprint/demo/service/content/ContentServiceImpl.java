package pawprint.demo.service.content;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pawprint.demo.domain.Content;
import pawprint.demo.domain.Media;
import pawprint.demo.domain.Member;
import pawprint.demo.repository.ContentRepository;
import pawprint.demo.repository.MediaRepository;
import pawprint.demo.repository.MemberRepository;
import pawprint.demo.service.S3Service;
import pawprint.demo.web.dto.ContentRequest;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService {
    
    private final ContentRepository contentRepository;
    private final MemberRepository memberRepository;
    private final MediaRepository mediaRepository;
    private final S3Service s3Service;
    
    @Override
    public Content save(ContentRequest.ContentCreateDto request, List<MultipartFile> files) {
        
        Member findMember = memberRepository.findById(request.getMemberId()).orElseThrow(
                () -> new IllegalArgumentException("Member Not Found")
        );
        
        Content newContent = Content.builder()
                .body(request.getBody())
                .member(findMember)
                .commentsCount(0)
                .likesCount(0)
                .build();
        
        contentRepository.save(newContent);
        contentRepository.flush();
        for (MultipartFile image : files) {
            String imageAddress = s3Service.uploadFile(image);
            Media media = Media.builder()
                    .filePath(imageAddress)
                    .content(newContent)
                    .build();
            mediaRepository.save(media);
        }
        
        return newContent;
    }
}
