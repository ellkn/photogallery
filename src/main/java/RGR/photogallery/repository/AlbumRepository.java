package RGR.photogallery.repository;

import RGR.photogallery.domain.Album;
import org.springframework.data.repository.CrudRepository;

public interface AlbumRepository extends CrudRepository<Album, Long> {

}
