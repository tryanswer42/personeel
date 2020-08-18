package be.vdab.personeel.controllers;
/**
 * @author Mulangu C
 */

import be.vdab.personeel.services.WerknemerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/werknemers")
class WerknemersController {
    private final WerknemerService werknemerService;

    WerknemersController(WerknemerService werknemerService) {
        this.werknemerService = werknemerService;
    }

    @GetMapping("{id}")
    public ModelAndView werknemers(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("werknemers");
        werknemerService.findById(id).ifPresent(werknemer ->
                modelAndView.addObject("werknemer", werknemer)
                        .addObject("ondergeschikten", werknemerService.findAllByChef_Id(werknemer.getId())))
        ;

        return modelAndView;
    }
}