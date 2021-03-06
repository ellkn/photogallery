package RGR.photogallery.controller;

import RGR.photogallery.domain.Album;
import RGR.photogallery.domain.Image;
import RGR.photogallery.domain.User;
import RGR.photogallery.repository.AlbumRepository;
import RGR.photogallery.repository.ImageRepository;
import RGR.photogallery.repository.UserRepository;
import RGR.photogallery.service.AlbumService;
import org.dom4j.rule.Mode;
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

import java.util.List;
import java.util.Optional;

@Controller
public class AlbumController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ImageRepository imageRepository;


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
            model.setViewName("redirect:/user/profile");
            return model;
        } else model.setViewName("addAlbum");
        return model;
    }

    @GetMapping("/deleteAlbum/{id}")
    public ModelAndView deleteAlbum(ModelAndView model, @PathVariable(name = "id") Long albumId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        albumService.deleteAlbum(albumId, auth.getName());
        model.setViewName("redirect:/user/profile");
        return model;
    }

    @GetMapping("/album/{id}")
    public ModelAndView albumPage(ModelAndView model, @PathVariable(name = "id") Long albumId) {
        List<Image> imageList = imageRepository.findAllByAlbumId(albumId);
        Album album = albumRepository.findById(albumId).get();
        User user = userRepository.findById(album.getUserId()).get();
        model.addObject("imageList", imageList);
        model.addObject("album", album);
        model.addObject("user", user);
        model.setViewName("album");
        return model;
    }

    @GetMapping("/album")
    public ModelAndView searchAlbumPage(ModelAndView model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepository.findByEmail(auth.getName());
        model.addObject("user", user.get());
        model.addObject("album", new Album());
        model.setViewName("searchalbum");
        return model;
    }

    @GetMapping("/searchAlbumByTitile")
    public ModelAndView searchAlbum(ModelAndView model, @ModelAttribute("album") Album album) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepository.findByEmail(auth.getName());
        model.addObject("user", user.get());
        model.addObject("album", new Album());
        List<Album> albums = albumRepository.findAllByTitleIsContaining(album.getTitle());
        model.addObject("albums", albums);
        if (albums.isEmpty()) {
            model.setViewName("redirect:/album");
            return model;
        } else
        model.setViewName("albumList");
        return model;
    }

    @GetMapping("/userAlbum/{id}")
    public ModelAndView userAlbumPage(ModelAndView model, @PathVariable(name = "id") Long id) {
        List<Image> imageList = imageRepository.findAllByAlbumId(id);
        Album album = albumRepository.findById(id).get();
        User user = userRepository.findById(album.getUserId()).get();
        model.addObject("imageList", imageList);
        model.addObject("album", album);
        model.addObject("user", user);
        model.setViewName("userAlbum");

        return model;
    }

}
