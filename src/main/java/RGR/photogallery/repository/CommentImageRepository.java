package RGR.photogallery.repository;

import RGR.photogallery.domain.CommentImage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentImageRepository extends CrudRepository<CommentImage, Long> {
    List<CommentImage> findAllByImageId(Long imageId);
}
