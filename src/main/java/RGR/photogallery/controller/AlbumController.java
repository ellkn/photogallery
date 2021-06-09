package RGR.photogallery.controller;

import RGR.photogallery.domain.Album;
import RGR.photogallery.domain.User;
import RGR.photogallery.repository.AlbumRepository;
import RGR.photogallery.repository.UserRepository;
import RGR.photogallery.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class AlbumController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private AlbumRepository albumRepository;


    @GetMapping("/add_album_page")
    public ModelAndView addAlbumPage(ModelAndView model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepository.findByEmail(auth.getName());
        model.addObject("album", new Album());
        model.addObject("user", user.get());
        model.setViewName("addAlbum");
        return model;
    }

    @PostMapping("/addAlbum")
    public ModelAndView addAlbum(ModelAndView model, @ModelAttribute(name = "album") Album album) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepository.findByEmail(auth.getName());
        if (albumService.addAlbum(user.get().getId(), album.getTitle())) {
            model.setViewName("/user/profile");
            return model;
        } else
            model.setViewName("addAlbum");
        return model;
    }

    @GetMapping("/album/{id}")
    public String albumPage(Model model, @PathVariable(name = "id") Long albumId) {
        Album album = albumRepository.findById(albumId).get();
        model.addAttribute("album", album);
        return "album";
    }
}
