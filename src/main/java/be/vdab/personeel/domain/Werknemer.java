package be.vdab.personeel.domain;
/**
 * @author Mulangu C
 */

import be.vdab.personeel.constraints.Rijksregisternr;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "werknemers")

@NamedEntityGraph(name = Werknemer.WITH_CHEFANDJOBTITELS,
        attributeNodes = {@NamedAttributeNode(value = "jobtitel"), @NamedAttributeNode(value = "chef")}
        , subgraphs = @NamedSubgraph(name = "withJobtitel", attributeNodes = @NamedAttributeNode("jobtitel")))

public class Werknemer {


    public static final String WITH_CHEFANDJOBTITELS = "Werknemer.withChefAndJobtitels";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String familienaam;

    @NotBlank
    private String voornaam;

    @Email
    private String email;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "chefid")
    private Werknemer chef;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "jobtitelid")
    private Jobtitel jobtitel;

    @NotNull
    @Positive
    @Min(1)
    @NumberFormat(pattern = "0.00")
    private BigDecimal salaris;

    @NotBlank
    private String paswoord;

    @Past
    private Date geboorte;

    @NotNull
    @Rijksregisternr
    private long rijksregisternr;

    @Version
    private int versie;

    @OneToMany()
    private Set<Werknemer> ondergeschikten;

    protected Werknemer() {
    }


    public Werknemer(@NotBlank String familienaam, @NotBlank String voornaam, @Email String email, Werknemer chef, Jobtitel jobtitel, @NotNull @Positive @Min(1) BigDecimal salaris, @NotBlank String paswoord, @Past Date geboorte, long rijksregisternr) {
        this.familienaam = familienaam;
        this.voornaam = voornaam;
        this.email = email;
        this.chef = chef;
        this.jobtitel = jobtitel;
        this.salaris = salaris;
        this.paswoord = paswoord;
        this.geboorte = geboorte;
        this.rijksregisternr = rijksregisternr;
    }

    public long getId() {
        return id;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getEmail() {
        return email;
    }

    public Werknemer getChef() {
        return chef;
    }

    public Jobtitel getJobtitel() {
        return jobtitel;
    }

    public BigDecimal getSalaris() {
        return salaris;
    }


    public Date getGeboorte() {
        return geboorte;
    }

    public long getRijksregisternr() {
        return rijksregisternr;
    }


    public Set<Werknemer> getOndergeschikten() {
        return ondergeschikten;
    }


    public void setSalaris(@NotNull @Positive @Min(1) BigDecimal salaris) {
        this.salaris = salaris;
    }


    public void setRijksregisternr(long rijksregisternr) {

        this.rijksregisternr = rijksregisternr;
    }
}
