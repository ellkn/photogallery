package RGR.photogallery.controller;

import RGR.photogallery.service.ImageServiceDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ImageController {
    @Autowired
    ImageServiceDomain imageServiceDomain;
}
