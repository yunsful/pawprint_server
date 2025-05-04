package pawprint.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pawprint.demo.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {


}
