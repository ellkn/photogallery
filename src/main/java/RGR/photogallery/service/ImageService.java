package RGR.photogallery.service;

import RGR.photogallery.domain.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface ImageService {
    void save(Image image);
    void delete(Image image);
    Image findById(Long id);
}
