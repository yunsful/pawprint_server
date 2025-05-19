package pawprint.demo.service.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pawprint.demo.apiPayload.code.status.ErrorStatus;
import pawprint.demo.apiPayload.exception.handler.MemberHandler;
import pawprint.demo.domain.Member;
import pawprint.demo.repository.MemberRepository;
import pawprint.demo.service.S3Service;
import pawprint.demo.web.dto.MemberRequest;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final S3Service s3Service;
    
    @Override
    public Member join(MultipartFile profileImage, MemberRequest.MemberJoinDto joinDto) {
        
        if (memberRepository.existsByUserId(joinDto.getUserId())) {
            throw new MemberHandler(ErrorStatus.Member_ALREADY_EXIST);
        }
        
        String imageAddress = null;
        
        if (profileImage != null) {
            imageAddress = s3Service.uploadFile(profileImage);
        }
        
        Member newMember = Member.builder()
                .profile(imageAddress)
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
    
    @Override
    public Member delete(Long id) {
        
        Member findMember = memberRepository.findById(id).orElseThrow(
                () -> new MemberHandler(ErrorStatus.Member_NOT_FOUND)
        );
        s3Service.deleteImageFromS3(findMember.getProfile());
        memberRepository.delete(findMember);
        memberRepository.flush();
        
        memberRepository.findById(id).ifPresent(member -> {
            throw new MemberHandler(ErrorStatus._INTERNAL_SERVER_ERROR);
        });
        
        return findMember;
    }
}
