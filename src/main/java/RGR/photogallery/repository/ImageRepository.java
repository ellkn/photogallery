package RGR.photogallery.repository;

import RGR.photogallery.domain.Image;
import org.springframework.data.repository.CrudRepository;


public interface ImageRepository extends CrudRepository<Image, Long> {


}
