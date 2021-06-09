package RGR.photogallery.service;

import RGR.photogallery.domain.Album;
import RGR.photogallery.domain.Image;
import RGR.photogallery.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceDomain implements ImageService{
    @Autowired
    ImageRepository imageRepository;

    public void save(Image image){
        imageRepository.save(image);
    }
    public void delete(Image image) {
        imageRepository.delete(image);
    }
}
