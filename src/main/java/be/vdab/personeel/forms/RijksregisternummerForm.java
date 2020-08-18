package be.vdab.personeel.forms;
/**
 * @author Mulangu C
 */

import be.vdab.personeel.constraints.Rijksregisternr;

import javax.validation.constraints.NotNull;

public class RijksregisternummerForm {


    @NotNull
    @Rijksregisternr
    private final Long rijksregisternummer;


    public RijksregisternummerForm(@NotNull Long rijksregisternummer) {
        this.rijksregisternummer = rijksregisternummer;
    }

    public Long getRijksregisternummer() {
        return rijksregisternummer;
    }
}
