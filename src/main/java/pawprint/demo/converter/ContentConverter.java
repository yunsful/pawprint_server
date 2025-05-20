package pawprint.demo.converter;

import pawprint.demo.domain.Content;
import pawprint.demo.domain.Media;
import pawprint.demo.web.dto.ContentResponse;

import java.util.List;

public class ContentConverter {
    
    public static ContentResponse.ContentInfoDto toContentInfoDto(Content content, List<Media> mediaList) {
        
        List<String> images = mediaList.stream().map(Media::getFilePath).toList();
        
        return ContentResponse.ContentInfoDto.builder()
                .contentId(content.getId())
                .body(content.getBody())
                .images(images)
                .commentsCount(content.getCommentsCount())
                .likesCount(content.getLikesCount())
                .build();
    }
    
}
