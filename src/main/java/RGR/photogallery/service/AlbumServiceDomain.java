package RGR.photogallery.service;

import RGR.photogallery.domain.Album;
import RGR.photogallery.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceDomain implements AlbumService{
    @Autowired
    AlbumRepository albumRepository;

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

}
