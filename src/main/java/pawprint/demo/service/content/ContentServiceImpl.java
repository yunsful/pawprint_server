package pawprint.demo.service.content;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pawprint.demo.apiPayload.code.status.ErrorStatus;
import pawprint.demo.apiPayload.exception.handler.ContentHandler;
import pawprint.demo.apiPayload.exception.handler.MemberHandler;
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
                () -> new MemberHandler(ErrorStatus.Member_NOT_FOUND)
        );
        
        Content newContent = Content.builder()
                .body(request.getBody())
                .member(findMember)
                .commentsCount(0)
                .likesCount(0)
                .build();
        
        contentRepository.save(newContent);
        contentRepository.flush();
        if (files != null && !files.isEmpty()) {
            for (MultipartFile image : files) {
                String imageAddress = s3Service.uploadFile(image);
                Media media = Media.builder()
                        .filePath(imageAddress)
                        .content(newContent)
                        .build();
                mediaRepository.save(media);
            }
        }
        
        return newContent;
    }
    
    @Override
    public Content update(ContentRequest.ContentUpdateDto request, List<MultipartFile> files) {
        
        Member findMember = memberRepository.findById(request.getMemberId()).orElseThrow(
                () -> new MemberHandler(ErrorStatus.Member_NOT_FOUND)
        );
        
        Content findContent = contentRepository.findById(request.getContentId()).orElseThrow(
                () -> new ContentHandler(ErrorStatus.CONTENT_NOT_FOUND)
        );
        
        if (!findContent.getMember().getId().equals(findMember.getId())) {
            throw new ContentHandler(ErrorStatus._UNAUTHORIZED);
        }
        
        List<Media> images = mediaRepository.findAllByContent(findContent).isEmpty() ? List.of() : mediaRepository.findAllByContent(findContent);
        
        if (!images.isEmpty()) {
            for (Media image : images) {
                s3Service.deleteImageFromS3(image.getFilePath());
                mediaRepository.delete(image);
                mediaRepository.flush();
            }
        }
        
        findContent.update(request.getBody());
        
        if (files != null && !files.isEmpty()) {
            for (MultipartFile image : files) {
                String imageAddress = s3Service.uploadFile(image);
                
                Media media = Media.builder()
                        .filePath(imageAddress)
                        .content(findContent)
                        .build();
                
                mediaRepository.save(media);
                mediaRepository.flush();
            }
        }
        contentRepository.flush();
        return findContent;
    }
    
    @Override
    public Content getById(Long id) {
        
        return contentRepository.findById(id).orElseThrow(
                () -> new ContentHandler(ErrorStatus.CONTENT_NOT_FOUND)
        );
    }
    
    @Override
    public void deleteById(Long id) {
        List<Media> images = mediaRepository.findAllByContent_Id(id);
        for (Media image : images) {
            s3Service.deleteImageFromS3(image.getFilePath());
            mediaRepository.delete(image);
            mediaRepository.flush();
        }
        contentRepository.deleteById(id);
        contentRepository.flush();
        
        contentRepository.findById(id).ifPresent(content -> {
            throw new ContentHandler(ErrorStatus._INTERNAL_SERVER_ERROR);
        });
    }
}
