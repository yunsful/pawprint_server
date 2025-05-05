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
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Override
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
    
    @Override
    public Member login(MemberRequest.MemberLoginDto loginDto) {
        Member loginMember = memberRepository.findByUserId(loginDto.getUserId()).orElseThrow(
                () -> new MemberHandler(ErrorStatus.Member_NOT_FOUND)
        );
        
        if (!bCryptPasswordEncoder.matches(loginDto.getPassword(), loginMember.getPassword())) {
            throw new MemberHandler(ErrorStatus.NOT_VALID_PASSWORD);
        }
        
        return loginMember;
    }
    
    @Override
    public Member update(MemberRequest.MemberUpdateDto updateDto) {
        Member findMember = memberRepository.findById(updateDto.getId()).orElseThrow(
                () -> new MemberHandler(ErrorStatus.Member_NOT_FOUND)
        );
        
        findMember.update(updateDto.getStatusNote(), updateDto.getName());
        
        return memberRepository.save(findMember);
    }
}
