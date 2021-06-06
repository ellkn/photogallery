package RGR.photogallery.controller;

import RGR.photogallery.form.UserRegistrationForm;
import RGR.photogallery.form.UserRegistrationFormValidator;
import RGR.photogallery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    private UserRegistrationFormValidator userFormValidator;

    @InitBinder("userForm")
    private void initBinder(WebDataBinder binder) {
        binder.addValidators(userFormValidator);
    }

    @GetMapping("/user/list")
    public String index(Model model) {

        model.addAttribute("users", userService.getList());

        return "/user/list";
    }

    @GetMapping("/user/registration")
    public ModelAndView registration(ModelAndView model) {
        model.addObject("userForm", new UserRegistrationForm());
        model.setViewName("/user/registration");

        return model;
    }

    @PostMapping("/user/registration")
    public ModelAndView registrationPost(ModelAndView model,
                                         @Valid @ModelAttribute("userForm") UserRegistrationForm userForm,
                                         BindingResult result) {

        System.out.println("Email: " + userForm.getEmail());
        System.out.println("Password: " + userForm.getPassword());

        if(result.hasErrors()) {
            model.addObject("userForm", userForm);
            model.setViewName("/user/registration");
        } else {

            model.setViewName("redirect:/");
        }
        return model;
    }

}
