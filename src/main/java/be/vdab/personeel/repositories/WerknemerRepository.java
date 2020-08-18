package be.vdab.personeel.repositories;
/**
 * @author Mulangu C
 */

import be.vdab.personeel.domain.Werknemer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WerknemerRepository extends JpaRepository<Werknemer, Long> {
    @EntityGraph(Werknemer.WITH_CHEFANDJOBTITELS)
    List<Werknemer> findAllByChef_Id(Long chefId);

    @EntityGraph(Werknemer.WITH_CHEFANDJOBTITELS)
    List<Werknemer> findAllByJobtitel_Id(Long jobId);

    @Override
    Optional<Werknemer> findById(Long aLong);

    @EntityGraph(Werknemer.WITH_CHEFANDJOBTITELS)
    Optional<Werknemer> findFirstByChefIsNull();


}
