package be.vdab.personeel.controllers;
/**
 * @author Mulangu C
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// enkele imports
@Controller
@RequestMapping("login")
class LoginController {
    @GetMapping
    public String login() {
        return "login";
    }
}