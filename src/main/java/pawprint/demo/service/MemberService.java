package pawprint.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pawprint.demo.apiPayload.code.status.ErrorStatus;
import pawprint.demo.apiPayload.exception.handler.MemberHandler;
import pawprint.demo.domain.Member;
import pawprint.demo.repository.MemberRepository;
import pawprint.demo.web.dto.MemberRequest;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public Member join(MemberRequest.MemberJoinDto joinDto) {
        
        if (memberRepository.existsByUserId(joinDto.getUserId())) {
            throw new MemberHandler(ErrorStatus.Member_ALREADY_EXIST);
        }
        
        Member newMember = Member.builder()
                .userId(joinDto.getUserId())
                .password(bCryptPasswordEncoder.encode(joinDto.getPassword()))
                .name(joinDto.getName())
                .statusNote(joinDto.getStatusNote())
                .build();
        
        return memberRepository.save(newMember);
    }
}
