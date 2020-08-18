package be.vdab.personeel.controllers;
/**
 * @author Mulangu C
 */

import be.vdab.personeel.services.JobtitelService;
import be.vdab.personeel.services.WerknemerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/jobtitels")
public class JobtitelController {
    private final WerknemerService werknemerService;
    private final JobtitelService jobtitelService;

    public JobtitelController(WerknemerService werknemerService, JobtitelService jobtitelService) {
        this.werknemerService = werknemerService;
        this.jobtitelService = jobtitelService;
    }

    @GetMapping
    public ModelAndView jobtitels() {
        ModelAndView modelAndView = new ModelAndView("jobtitels");
        modelAndView.addObject("jobtitels", jobtitelService.findAll());


        return modelAndView;
    }

    @GetMapping("{id}")
    public ModelAndView jobtitel(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("jobtitels");
        modelAndView.addObject("jobtitels", jobtitelService.findAll());
        modelAndView.addObject("jobWerknemers", werknemerService.findAllByJobtitel_Id(id));


        return modelAndView;
    }


}
