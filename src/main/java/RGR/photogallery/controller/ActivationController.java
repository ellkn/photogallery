package RGR.photogallery.controller;

import RGR.photogallery.service.UserService;
import RGR.photogallery.service.UserServiceDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ActivationController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserServiceDomain userServiceDomain;

    @GetMapping("/activate/{code}")
    public String activateUser(@PathVariable String code) {
        if (userServiceDomain.activateUser(code)) {
            return "redirect:/";
        } else
            return "redirect:/activate_error";
    }

    @GetMapping("/activate_error")
    public String errorActive() {
        return "login";
    }
}
