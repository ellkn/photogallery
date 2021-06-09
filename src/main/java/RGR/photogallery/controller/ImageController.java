package RGR.photogallery.controller;

import RGR.photogallery.domain.Image;
import RGR.photogallery.service.AlbumService;
import RGR.photogallery.service.CommentService;
import RGR.photogallery.service.ImageService;
import RGR.photogallery.service.ImageServiceDomain;
import RGR.photogallery.util.ProfileImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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
    ProfileImageService profileImageService;

    @GetMapping("/addPhoto")
    public ModelAndView avatarUpload(ModelAndView modelAndView, @RequestParam("imageId") Optional<Long> imageId) {
        if (imageId.isEmpty()) {
            modelAndView.setViewName("redirect:/album/{id}");
        } else {
            modelAndView.addObject("imageId", imageId.get());
            modelAndView.setViewName("/addPhoto");
        }
        return modelAndView;
    }

    @PostMapping("/addPhoto")
    public ModelAndView avatarUploadProcessing(@RequestParam("files") MultipartFile[] files,
                                               @RequestParam("imageId") Optional<Long> imageId,
                                               ModelAndView modelAndView)
    {

        modelAndView.setViewName("redirect:/album/{id}");

        if (imageId.isPresent()) {
            Image image = imageService.findById(imageId.get());

            if (image != null) {
                for (MultipartFile multipartFile : files) {
                    if (!profileImageService.saveProfileImage(multipartFile, imageId.get())) {
                        modelAndView.setViewName("redirect:/upload-error");
                        break;
                    }
                }
            }
        }

        return modelAndView;
    }

    @GetMapping("/upload-error")
    public String uploadError() {
        return "/addPhoto";
    }

}
