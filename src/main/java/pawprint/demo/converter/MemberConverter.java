package pawprint.demo.converter;

import pawprint.demo.domain.Member;
import pawprint.demo.web.dto.MemberResponse;

public class MemberConverter {
    
    public static MemberResponse.MemberIdDto toMemberIdDto(Member member) {
        return MemberResponse.MemberIdDto.builder()
                .id( member.getId())
                .build();
    }
}
