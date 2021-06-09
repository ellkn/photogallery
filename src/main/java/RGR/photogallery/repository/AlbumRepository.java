package RGR.photogallery.repository;

import RGR.photogallery.domain.Album;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AlbumRepository extends CrudRepository<Album, Long> {
    List<Album> findAllByUserId(Long userId);
    Album findByTitle(String title);

}
