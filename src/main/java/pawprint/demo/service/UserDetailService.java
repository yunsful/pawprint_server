package pawprint.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pawprint.demo.domain.Member;
import pawprint.demo.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class UserDetailService {
    
    private final MemberRepository memberRepository;
}