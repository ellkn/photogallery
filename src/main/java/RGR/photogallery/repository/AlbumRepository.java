package RGR.photogallery.repository;

import RGR.photogallery.domain.Album;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AlbumRepository extends CrudRepository<Album, Long> {
    List<Album> findAllByUserId(Long userId);
    Album findByTitle(String title);
    Optional<Album> findById(Long id);
    List<Album> findAllByTitleIsContaining(String title);
}
