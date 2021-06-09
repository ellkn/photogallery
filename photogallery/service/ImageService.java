package RGR.photogallery.service;

import RGR.photogallery.domain.Album;
import RGR.photogallery.domain.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ImageService {
    void save(Image image);
    void delete(Image image);
}
