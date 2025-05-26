package pawprint.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pawprint.demo.domain.Member;
import pawprint.demo.domain.Pet;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    
    
    List<Pet> findFirstByMember(Member member);
}
