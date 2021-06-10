package RGR.photogallery.repository;

import RGR.photogallery.domain.Image;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface ImageRepository extends CrudRepository<Image, Long> {

    List<Image> findAllByAlbumId(Long id);

}
