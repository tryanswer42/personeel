package be.vdab.personeel.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

class WerknemerTest {
    private Validator validator;
    private Werknemer werknemer;
    private Werknemer werknemer2;
    private Jobtitel jobtitel;

    @BeforeEach
    void beforeEach() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        werknemer = new Werknemer("bob", "tim", "dodo@test.be", werknemer2, jobtitel, BigDecimal.TEN, "test", GregorianCalendar.getInstance().getTime(), 11111111111L);
    }

    @Test
    void correctRijksregisternrVoor2000() {
        werknemer.setRijksregisternr(50013100129L);
        assertThat(validator.validate(werknemer)).isEmpty();
    }

    @Test
    void correctRijksregisternrNa2000() {
        werknemer.setRijksregisternr(9082428248L);
        assertThat(validator.validate(werknemer)).isEmpty();
    }

    @Test
    void verkeerdRijksregisternr() {
        werknemer.setRijksregisternr(6013100129L);
        assertThat(validator.validate(werknemer)).isNotEmpty();
    }

    @Test
    void verkeerdRijksregisternrNa2000() {
        werknemer.setRijksregisternr(19082428248L);
        assertThat(validator.validate(werknemer)).isNotEmpty();
    }


}