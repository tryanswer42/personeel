package be.vdab.personeel.controllers;
/**
 * @author Mulangu C
 */

import be.vdab.personeel.forms.OpslagForm;
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
@RequestMapping("/opslag")
public class OpslagController {
    private final WerknemerService werknemerService;

    OpslagController(WerknemerService werknemerService) {
        this.werknemerService = werknemerService;
    }

    @GetMapping("{id}")
    public ModelAndView opslag(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("opslag");
        werknemerService.findById(id).ifPresent(werknemer ->
        {
            modelAndView.addObject("werknemer", werknemer);
            modelAndView.addObject(new OpslagForm(null));
        });
        return modelAndView;
    }

    @PostMapping("{id}")
    public ModelAndView opslagUpdaten(@Valid OpslagForm opslagForm, Errors errors, @PathVariable("id") long id) {
        if (errors.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("opslag");
            werknemerService.findById(id).ifPresent(werknemer ->
            {
                modelAndView.addObject("werknemer", werknemer);
                modelAndView.addObject(errors);

            });

            return modelAndView;
        }
        werknemerService.findById(id).ifPresent(werknemer -> werknemerService.opslagUpdaten(werknemer, opslagForm.getOpslag()));

        return new ModelAndView("redirect:/opslag/" + id);
    }
}
