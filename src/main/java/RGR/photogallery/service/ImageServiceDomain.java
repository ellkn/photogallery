package RGR.photogallery.service;

import RGR.photogallery.domain.Image;
import RGR.photogallery.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceDomain implements ImageService{
    @Autowired
    ImageRepository imageRepository;

    @Override
    public boolean addPhotoAlbum(Long albumId, String title, String tags, String file) {
        Image image = new Image();

        image.setAlbumId(albumId);
        image.setTitle(title);
        image.setFile(file);
        image.setTags(tags);

        imageRepository.save(image);
        return true;
    }

    @Override
    public boolean deletePhoto(Long id) {
        Image image = imageRepository.findById(id).get();
        imageRepository.delete(image);
        return true;
    }
}
