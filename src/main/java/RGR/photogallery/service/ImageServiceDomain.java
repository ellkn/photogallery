package RGR.photogallery.service;

import RGR.photogallery.domain.Album;
import RGR.photogallery.domain.Image;
import RGR.photogallery.domain.User;
import RGR.photogallery.repository.AlbumRepository;
import RGR.photogallery.repository.ImageRepository;
import RGR.photogallery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceDomain implements ImageService{
    @Autowired
    ImageRepository imageRepository;

    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    UserRepository userRepository;

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
    public boolean deletePhoto(Long id, String email) {
        Image image = imageRepository.findById(id).get();
        User user = userRepository.findByEmail(email).get();
        Album album = albumRepository.findById(image.getAlbumId()).get();

        if ((user.getId().equals(album.getUserId())) || (user.getRoles().contains("ADMIN")) || (user.getRoles().contains("MANAGER"))) {
            imageRepository.delete(image);
            return true;
        } else return false;
    }
}
