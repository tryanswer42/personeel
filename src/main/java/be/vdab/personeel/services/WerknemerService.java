package be.vdab.personeel.services;
/**
 * @author Mulangu C
 */

import be.vdab.personeel.domain.Werknemer;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface WerknemerService {

    List<Werknemer> findAllByChef_Id(Long chefId);


    List<Werknemer> findAllByJobtitel_Id(Long jobId);

    Optional<Werknemer> findById(Long aLong);

    Optional<Werknemer> findFirstByChefIsNull();


    void opslagUpdaten(Werknemer werknemer, BigDecimal nieuwOpslag);


    void rijksregisternummerUpdaten(Werknemer werknemer, Long nieuwRijksregisternummer);


}
