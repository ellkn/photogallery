package RGR.photogallery.service;

import RGR.photogallery.domain.Album;
import RGR.photogallery.domain.User;
import RGR.photogallery.repository.AlbumRepository;
import RGR.photogallery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceDomain implements AlbumService{
    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean addAlbum(Long userId, String title) {
        if (albumRepository.findByTitle(title) == null) {
            Album album = new Album();

            album.setTitle(title);
            album.setUserId(userId);
            album.setShared(false);

            albumRepository.save(album);

            return true;
        } else return false;
    }

    @Override
    public List<Album> allAlbumByUser(Long userId) {
        return albumRepository.findAllByUserId(userId);
    }

    @Override
    public boolean deleteAlbum(Long albumId, String email) {
        User user = userRepository.findByEmail(email).get();
        Album album = albumRepository.findById(albumId).get();

        if ((user.getId().equals(album.getUserId())) || (user.getRoles().contains("ADMIN")) || (user.getRoles().contains("MANAGER"))) {
            albumRepository.delete(album);
            return true;
        } else return false;
    }
}
