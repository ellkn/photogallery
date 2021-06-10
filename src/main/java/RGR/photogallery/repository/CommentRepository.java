package RGR.photogallery.repository;

import RGR.photogallery.domain.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findAllByImageId(Long imageId);
}
