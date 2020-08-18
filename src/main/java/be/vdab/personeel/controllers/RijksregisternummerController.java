package be.vdab.personeel.controllers;
/**
 * @author Mulangu C
 */

import be.vdab.personeel.forms.RijksregisternummerForm;
import be.vdab.personeel.services.WerknemerService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/rijksregisternummer")
public class RijksregisternummerController {
    private final WerknemerService werknemerService;

    RijksregisternummerController(WerknemerService werknemerService) {
        this.werknemerService = werknemerService;
    }

    @GetMapping("{id}")
    public ModelAndView rijksregisternummer(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("rijksregisternummer");
        werknemerService.findById(id).ifPresent(werknemer ->
        {
            modelAndView.addObject("werknemer", werknemer);
            modelAndView.addObject(new RijksregisternummerForm(null));
        });
        return modelAndView;
    }

    @PostMapping("{id}")
    public ModelAndView rijksregisterUpdaten(@Valid RijksregisternummerForm rijksregisternummerForm, Errors errors, @PathVariable("id") long id) {
        if (errors.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("rijksregisternummer");
            werknemerService.findById(id).ifPresent(werknemer ->
            {
                modelAndView.addObject("werknemer", werknemer);
                modelAndView.addObject(errors);

            });

            return modelAndView;
        }
        werknemerService.findById(id).ifPresent(werknemer -> werknemerService.rijksregisternummerUpdaten(werknemer, rijksregisternummerForm.getRijksregisternummer()));

        return new ModelAndView("redirect:/werknemers/" + id);
    }
}

