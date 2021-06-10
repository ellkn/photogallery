package RGR.photogallery.controller;

import RGR.photogallery.domain.Album;
import RGR.photogallery.domain.Comment;
import RGR.photogallery.domain.Image;
import RGR.photogallery.domain.User;
import RGR.photogallery.repository.AlbumRepository;
import RGR.photogallery.repository.ImageRepository;
import RGR.photogallery.repository.UserRepository;
import RGR.photogallery.service.AlbumService;
import RGR.photogallery.service.CommentService;
import RGR.photogallery.service.ImageService;
import RGR.photogallery.service.ImageServiceDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommentController {
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

    @PostMapping("/addComment/{imageID}")
    public ModelAndView addComment(ModelAndView model, @PathVariable(name = "imageID") Long imageId, @RequestParam(name = "comment") String comment) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName()).get();
        Image image = imageRepository.findById(imageId).get();
        Album album = albumRepository.findById(image.getAlbumId()).get();
        System.out.println(comment);

        commentService.addComment(comment, user.getUsername(), imageId);

        if (user.getId() != album.getUserId()) {
            model.setViewName("redirect:/image_at_user_album/" + imageId);
            return model;
        } else
        model.setViewName("redirect:/image_at_album/" + imageId);
        return model;
    }



}
