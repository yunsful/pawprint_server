package pawprint.demo.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

public class ContentResponse {
    
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    @Schema(description = "게시글 id 반환 DTO")
    public static class ContentIdDto {
        
        private Long id;
    }
    
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    @Schema(description = "게시글 상세정보 반환 DTO")
    public static class ContentInfoDto {
        private Long contentId;
        private List<String> images;
        private String body;
        private LocalDateTime createdAt;
        private Integer likesCount;
        private Integer commentsCount;
    }
    
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    @Schema(description = "마이페이지 게시물 리스트 반환 DTO")
    public static class ContentListInfoDto {
        private String name;
        private String profile;
        private String statusNote;
        private List<ContentInfoDto> contents;
    }
    
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    @Schema(description = "커뮤니티 게시물 정보 반환 DTO")
    public static class CommunityContentInfoDto {
        private Long memberId;
        private String name;
        private String profile;
        private Long contentId;
        private List<String> images;
        private String body;
        private LocalDateTime createdAt;
        private Integer likesCount;
        private Integer commentsCount;
    }
    
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    @Schema(description = "커뮤니티 게시물 리스트 반환 DTO")
    public static class CommunityContentListInfoDto {
        List<CommunityContentInfoDto> contents;
    }
    
}
