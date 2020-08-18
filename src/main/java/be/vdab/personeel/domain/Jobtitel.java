package be.vdab.personeel.domain;
/**
 * @author Mulangu C
 */

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "jobtitels")
public class Jobtitel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String naam;

    @Version
    private int versie;

    protected Jobtitel() {
    }

    public Jobtitel(@NotBlank String naam) {
        this.naam = naam;
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

}
