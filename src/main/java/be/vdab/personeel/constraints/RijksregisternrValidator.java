package be.vdab.personeel.constraints;
/**
 * @author Mulangu C
 */

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class RijksregisternrValidator implements ConstraintValidator<Rijksregisternr, Long> {
    @Override
    public void initialize(Rijksregisternr constraintAnnotation) {

    }

    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {


        if (aLong == null) {
            return true;
        }


        if (aLong.toString().length() == 11) {
            long birthpart = (aLong / 100000);
            SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
            sdf.setLenient(false);//No interpretation precise object
            try {
                Date date = sdf.parse(Long.toString(birthpart));

            } catch (ParseException e) {
                return false;
            }
        }

        if (7 < aLong.toString().length() && aLong.toString().length() < 11) {
            long birthpart = (aLong / 100000) + 20000000;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            sdf.setLenient(false);//No interpretation precise object
            try {
                Date date = sdf.parse(Long.toString(birthpart));

            } catch (ParseException e) {
                return false;
            }
        }

        if (aLong.toString().length() <= 7) {
            return false;
        }
        //        actual year 2020    <= (centurial value(in 2000=20, in 2100=21)*100) +registeryear( in 1989=89,in2001=01)
        if (LocalDate.now().getYear() <= ((LocalDate.now().getYear() / 100) * 100) + (aLong / 1000000000)) {
            return aLong % 100 == (97 - ((aLong / 100) % 97));
        }

        return aLong % 100 == (97 - (((aLong + 200000000000L) / 100) % 97));


    }


}
