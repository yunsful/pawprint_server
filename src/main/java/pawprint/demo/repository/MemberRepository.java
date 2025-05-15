package pawprint.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pawprint.demo.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    
    
    Boolean existsByUserId(String userId);
    
    Optional<Member> findByUserId(String userId);
    
    Optional<Member> findById(Long id);
}
