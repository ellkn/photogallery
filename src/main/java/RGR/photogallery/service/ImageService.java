package RGR.photogallery.service;

import RGR.photogallery.domain.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface ImageService {
    boolean addPhotoAlbum(Long albumId, String title, String tags, String file, Long userId);
    boolean deletePhoto(Long id, String email);
}
