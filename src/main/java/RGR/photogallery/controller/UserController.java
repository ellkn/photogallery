package RGR.photogallery.controller;

import RGR.photogallery.domain.Album;
import RGR.photogallery.domain.Image;
import RGR.photogallery.domain.User;
import RGR.photogallery.form.UserRegistrationForm;
import RGR.photogallery.form.UserRegistrationFormValidator;
import RGR.photogallery.repository.UserRepository;
import RGR.photogallery.service.AlbumService;
import RGR.photogallery.service.UserService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRegistrationFormValidator userFormValidator;

    @Autowired
    private AlbumService albumService;

    @InitBinder("userForm")
    private void initBinder(WebDataBinder binder) {
        binder.addValidators(userFormValidator);
    }

    @GetMapping("/user/list")
    public String index(Model model) {

        model.addAttribute("users", userService.getList());

        return "/user/list";
    }
    @GetMapping("/admin/adminuserlist")
    public String indexadmin(Model model) {

        model.addAttribute("users", userService.getList());

        return "/admin/adminuserlist";
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
            userService.addUser(userForm.getUserName(), userForm.getFirstName(), userForm.getLastName(), userForm.getEmail(),userForm.getDate(), userForm.getPassword());
            model.setViewName("redirect:/");
        }
        return model;
    }

    @PostMapping("/success_login")
    public ModelAndView successLogin(ModelAndView model) {
        model.setViewName("redirect:/");

        return model;
    }
    @GetMapping("/user/profile")
    public String profile(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepository.findByEmail(auth.getName());
        model.addAttribute("user", user.get());
        if (albumService.allAlbumByUser(user.get().getId()) != null) {
            model.addAttribute("albums", albumService.allAlbumByUser(user.get().getId()));
            return "/user/profile";
        } else
            return "/user/profile";
    }
//    @GetMapping("/user/{id}")
//    public ModelAndView userprofile(ModelAndView model, @PathVariable(name = "id") User userid) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        Optional<User> user = userRepository.findById(userid.getId());
//        model.addObject("user", user);
//        model.setViewName("user/profile/{id}");
//        return model;
//    }

    @GetMapping("/change_avatar_page")
    public String changeAvaPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepository.findByEmail(auth.getName());
        model.addAttribute("user", user.get());

        return "user/changeAvatarPage";
    }

    @PostMapping("/change_avatar")
    public ModelAndView changeAva(ModelAndView model, @ModelAttribute("user") User user) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user1 = userRepository.findByEmail(auth.getName()).get();
        userService.changeAvatar(user1.getId(), user.getImage());
        model.setViewName("redirect:/user/profile");
        return model;
    }

    @GetMapping("/searchUser")
    public ModelAndView searchAlbum(ModelAndView model, @RequestParam("user") String user) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user1 = userRepository.findByEmail(auth.getName());
        model.addObject("user", user1.get());
        userRepository.findAllByFirstnameAndLastnameAndEmailAndUsername(user);
System.out.println(user);
        model.setViewName("user/list");
        return model;
    }


}
