package RGR.photogallery.controller;

import RGR.photogallery.domain.Album;
import RGR.photogallery.domain.Comment;
import RGR.photogallery.domain.Image;
import RGR.photogallery.domain.User;
import RGR.photogallery.repository.AlbumRepository;
import RGR.photogallery.repository.CommentRepository;
import RGR.photogallery.repository.ImageRepository;
import RGR.photogallery.repository.UserRepository;
import RGR.photogallery.service.AlbumService;
import RGR.photogallery.service.CommentService;
import RGR.photogallery.service.ImageService;
import RGR.photogallery.service.ImageServiceDomain;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class ImageController {
    @Autowired
    ImageService imageService;

    @Autowired
    ImageServiceDomain imageServiceDomain;

    @Autowired
    CommentService commentService;

    @Autowired
    AlbumService albumService;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @PostMapping("/addPhoto/{albumId}")
    public ModelAndView addImageAtAlbum(@RequestParam("file") MultipartFile file, @PathVariable(name = "albumId") Long albumId, ModelAndView model, @RequestParam String title, @RequestParam String tags) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepository.findByEmail(auth.getName());
        imageService.addPhotoAlbum(albumId, title, tags, file.getOriginalFilename(), user.get().getId());

        model.setViewName("redirect:/album/" + albumId);
        return model;
    }

    @GetMapping("/addPhotoPage/{albumId}")
    public ModelAndView albumPage(ModelAndView model, @PathVariable(name = "albumId") Long albumId) {
        Album album = albumRepository.findById(albumId).get();
        model.addObject("album", album);
        model.setViewName("addPhoto");
        return model;
    }

    @GetMapping("/image_at_album/{imageId}")
    public ModelAndView imagePage(ModelAndView model, @PathVariable(name = "imageId") Long imageId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepository.findByEmail(auth.getName());
        List<Comment> comments = commentRepository.findAllByImageId(imageId);
        model.addObject("user", user.get());
        Image image = imageRepository.findById(imageId).get();
        Album album = albumRepository.findById(image.getAlbumId()).get();
        model.addObject("comments", comments);
        model.addObject("image", image);
        model.addObject("album", album);
        model.setViewName("imagePage");
        return model;
    }

    @GetMapping("/deletePhoto/{id}")
    public ModelAndView deletePhoto(ModelAndView model, @PathVariable(name = "id") Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepository.findByEmail(auth.getName());
        imageService.deletePhoto(id, user.get().getId());
        model.setViewName("redirect:/user/profile");
        return model;
    }

    @GetMapping("/image_at_user_album/{imageId}")
    public ModelAndView imageUserPage(ModelAndView model, @PathVariable(name = "imageId") Long imageId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepository.findByEmail(auth.getName());
        List<Comment> comments = commentRepository.findAllByImageId(imageId);
        model.addObject("user", user.get());
        Image image = imageRepository.findById(imageId).get();
        Album album = albumRepository.findById(image.getAlbumId()).get();
        model.addObject("comments", comments);
        model.addObject("image", image);
        model.addObject("album", album);
        model.setViewName("imageByUserPage");
        return model;
    }


}
