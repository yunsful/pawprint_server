package pawprint.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pawprint.demo.domain.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {


}
