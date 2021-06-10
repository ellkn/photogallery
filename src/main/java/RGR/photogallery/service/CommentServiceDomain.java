package RGR.photogallery.service;

import RGR.photogallery.domain.Comment;
import RGR.photogallery.domain.CommentImage;
import RGR.photogallery.repository.CommentImageRepository;
import RGR.photogallery.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceDomain implements CommentService{
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    CommentImageRepository commentImageRepository;

    @Override
    public boolean addComment(String text, String username, Long imageId) {
        Comment comment = new Comment();
        comment.setText(text);
        comment.setUsername(username);
        comment.setImageId(imageId);

        commentRepository.save(comment);
        return true;
    }
}
