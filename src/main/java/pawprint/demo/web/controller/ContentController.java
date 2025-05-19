package pawprint.demo.web.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pawprint.demo.apiPayload.ApiResponse;
import pawprint.demo.domain.Content;
import pawprint.demo.service.content.ContentService;
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
    
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<ContentResponse.ContentIdDto> createContent(
            @Parameter(description = "생성할 게시글 정보를 입력하세요.")
            @RequestPart ContentRequest.ContentCreateDto request,
            @RequestPart List<MultipartFile> images
    ) {
        Content saved = contentService.save(request, images);
        return ApiResponse.onSuccess(ContentResponse.ContentIdDto.builder().id(saved.getId()).build());
    }
}
