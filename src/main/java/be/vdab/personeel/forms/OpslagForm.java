package be.vdab.personeel.forms;
/**
 * @author Mulangu C
 */

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class OpslagForm {

    //----------------------------------------------------------
    @NotNull
    @Positive
    @Min(1)
    private final BigDecimal opslag;

    public OpslagForm(BigDecimal opslag) {
        this.opslag = opslag;
    }

    public BigDecimal getOpslag() {
        return opslag;
    }
//----------------------------------------------------------

}