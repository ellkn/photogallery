package RGR.photogallery.service;

import RGR.photogallery.domain.Album;
import RGR.photogallery.domain.Image;
import RGR.photogallery.repository.AlbumRepository;
import RGR.photogallery.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceDomain implements ImageService{
    @Autowired
    ImageRepository imageRepository;

    @Autowired
    AlbumRepository albumRepository;

    @Override
    public boolean addPhotoAlbum(Long albumId, String title, String tags, String file, Long userId) {
        Image image = new Image();
        Album album = albumRepository.findById(albumId).get();


        image.setAlbumId(albumId);
        image.setTitle(title);
        image.setFile(file);
        image.setTags(tags);

        if (album.getUserId() == userId) {
            imageRepository.save(image);
            return true;
        } else return false;
    }

    @Override
    public boolean deletePhoto(Long id, Long userId) {
        Image image = imageRepository.findById(id).get();
        Album album = albumRepository.findById(image.getAlbumId()).get();

        if (album.getUserId() == userId) {
            imageRepository.delete(image);
            return true;
        } else return false;
    }
}
