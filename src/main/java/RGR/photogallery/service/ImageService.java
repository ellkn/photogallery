package RGR.photogallery.service;

import RGR.photogallery.domain.Image;

public interface ImageService {
    void save(Image image);
    void delete(Image image);
}
