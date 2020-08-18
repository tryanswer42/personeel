package be.vdab.personeel.services;
/**
 * @author Mulangu C
 */

import be.vdab.personeel.domain.Werknemer;
import be.vdab.personeel.repositories.WerknemerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class DefaultWerknemerService implements WerknemerService {

    private final WerknemerRepository werknemerRepository;


    public DefaultWerknemerService(WerknemerRepository werknemerRepository) {
        this.werknemerRepository = werknemerRepository;
    }

    @Override
    public List<Werknemer> findAllByChef_Id(Long chefId) {
        return werknemerRepository.findAllByChef_Id(chefId);
    }

    @Override
    public List<Werknemer> findAllByJobtitel_Id(Long jobId) {
        return werknemerRepository.findAllByJobtitel_Id(jobId);
    }

    @Override
    public Optional<Werknemer> findById(Long aLong) {
        return werknemerRepository.findById(aLong);
    }

    @Override
    public Optional<Werknemer> findFirstByChefIsNull() {
        return werknemerRepository.findFirstByChefIsNull();
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void opslagUpdaten(Werknemer werknemer, BigDecimal nieuwOpslag) {
        werknemer.setSalaris(nieuwOpslag);
        werknemerRepository.save(werknemer);

    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void rijksregisternummerUpdaten(Werknemer werknemer, Long nieuwRijksregisternummer) {
        if (werknemer.getRijksregisternr() != nieuwRijksregisternummer) {
            werknemer.setRijksregisternr(nieuwRijksregisternummer);
            werknemerRepository.save(werknemer);
        }
    }

}
