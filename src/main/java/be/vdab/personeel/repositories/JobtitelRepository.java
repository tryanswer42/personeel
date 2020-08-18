package be.vdab.personeel.repositories;
/**
 * @author Mulangu C
 */

import be.vdab.personeel.domain.Jobtitel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobtitelRepository extends JpaRepository<Jobtitel, Long> {

}
