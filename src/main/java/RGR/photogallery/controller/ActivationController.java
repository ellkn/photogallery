package RGR.photogallery.controller;

import RGR.photogallery.domain.User;
import RGR.photogallery.repository.UserRepository;
import RGR.photogallery.service.UserService;
import RGR.photogallery.service.UserServiceDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ActivationController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/activate/{code}")
    public String activateUser(@PathVariable(name = "code") String code) {
        if (userService.activateUser(code)) {
            return "redirect:/login";
        } else
            return "redirect:/activate_error";
    }

    @GetMapping("/activate_error")
    public String errorActive() {
        return "/user/registration";
    }
}
