package pawprint.demo.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pawprint.demo.apiPayload.ApiResponse;
import pawprint.demo.converter.ContentConverter;
import pawprint.demo.domain.Content;
import pawprint.demo.domain.Media;
import pawprint.demo.domain.Member;
import pawprint.demo.service.MediaService;
import pawprint.demo.service.content.ContentService;
import pawprint.demo.service.member.MemberService;
import pawprint.demo.web.dto.ContentRequest;
import pawprint.demo.web.dto.ContentResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/contents")
@Tag(name = "게시글", description = "게시글 관련 API")
@Slf4j
public class ContentController {
    
    private final ContentService contentService;
    private final MemberService memberService;
    private final MediaService mediaService;
    
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "게시글 생성", description = "데이터를 입력해 게시글을 생성합니다.")
    public ApiResponse<ContentResponse.ContentIdDto> createContent(
            @Parameter(description = "생성할 게시글 정보를 입력하세요.")
            @RequestPart ContentRequest.ContentCreateDto request,
            @RequestPart(required = false) List<MultipartFile> images
    ) {
        Content saved = contentService.save(request, images);
        return ApiResponse.onSuccess(ContentResponse.ContentIdDto.builder().id(saved.getId()).build());
    }
    
    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "게시글 수정", description = "데이터를 입력해 게시글을 수정합니다.")
    public ApiResponse<ContentResponse.ContentIdDto> updateContent(
            @Parameter(description = "수정할 게시글 정보를 입력하세요.")
            @RequestPart ContentRequest.ContentUpdateDto request,
            @RequestPart(required = false) List<MultipartFile> images
    ) {
        Content updatedContent = contentService.update(request, images);
        
        return ApiResponse.onSuccess(ContentResponse.ContentIdDto.builder().id(updatedContent.getId()).build());
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "게시글 상세 조회", description = "게시글을 상세 조회합니다.")
    public ApiResponse<ContentResponse.ContentInfoDto> getContent(
            @Parameter(description = "조회할 게시글 id를 입력하세요.")
            @PathVariable Long id
    ) {
        Content findContent = contentService.getById(id);
        List<Media> findImages = mediaService.getMediaByContentId(findContent.getId());
        
        return ApiResponse.onSuccess(ContentConverter.toContentInfoDto(findContent, findImages));
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "게시글 삭제", description = "게시글을 삭제합니다.")
    public ApiResponse<ContentResponse.ContentIdDto> deleteContent(
            @Parameter(description = "삭제할 게시글 id를 입력하세요.")
            @PathVariable Long id
    ) {
        contentService.deleteById(id);
        
        return ApiResponse.onSuccess(ContentResponse.ContentIdDto.builder().id(id).build());
    }
    
    @GetMapping("/members/{id}")
    @Operation(summary = "마이페이지 게시글 조회", description = "회원의 마이페이지 화면 정보를 조회합니다.")
    public ApiResponse<ContentResponse.ContentListInfoDto> getMemberContent(
            @Parameter(description = "회원 id를 입력하세요.")
            @PathVariable Long id
            ) {
        List<Content> contentList = contentService.getAllByMemberId(id);
        
        List<ContentResponse.ContentInfoDto> contentInfoDtoList = contentList.stream()
                .map(content -> ContentConverter.toContentInfoDto(
                        content, mediaService.getMediaByContentId(content.getId())
                        )
                ).toList();
        
        Member findMember = memberService.findById(id);
        return ApiResponse.onSuccess(ContentResponse.ContentListInfoDto.builder()
                .name(findMember.getName())
                .statusNote(findMember.getStatusNote())
                .profile(findMember.getProfile())
                .contents(contentInfoDtoList)
                .build());
                
    }
    
    @GetMapping()
    @Operation(summary = "커뮤니티 게시글 조회", description = "커뮤니티의 모든 게시글 정보를 조회합니다.")
    public ApiResponse<ContentResponse.CommunityContentListInfoDto> getCommunityContent() {
        
        ContentResponse.CommunityContentListInfoDto result = contentService.getAll();
        
        return ApiResponse.onSuccess(result);
    }
}
