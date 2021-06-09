package RGR.photogallery.repository;

import RGR.photogallery.domain.Album;
import RGR.photogallery.domain.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ImageRepository extends CrudRepository<Image, Long> {


}
