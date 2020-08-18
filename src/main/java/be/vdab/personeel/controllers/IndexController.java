package be.vdab.personeel.controllers;
/**
 * @author Mulangu C
 */

import be.vdab.personeel.services.WerknemerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// enkele imports
@Controller
@RequestMapping("/")
class IndexController {
    private final WerknemerService werknemerService;

    IndexController(WerknemerService werknemerService) {
        this.werknemerService = werknemerService;
    }

    @GetMapping
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        werknemerService.findFirstByChefIsNull().ifPresent(bigChef -> modelAndView.addObject("bigChefId", bigChef.getId()));


        return modelAndView;
    }
}