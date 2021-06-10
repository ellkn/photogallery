package RGR.photogallery.service;

import RGR.photogallery.domain.Comment;
import RGR.photogallery.domain.CommentImage;
import RGR.photogallery.domain.User;
import RGR.photogallery.repository.CommentImageRepository;
import RGR.photogallery.repository.CommentRepository;
import RGR.photogallery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceDomain implements CommentService{
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    CommentImageRepository commentImageRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean addComment(String text, String username, Long imageId) {
        Comment comment = new Comment();
        comment.setText(text);
        comment.setUsername(username);
        comment.setImageId(imageId);

        commentRepository.save(comment);
        return true;
    }

    @Override
    public boolean deleteComment(Long commentId, String email) {
        Comment comment = commentRepository.findById(commentId).get();
        User user = userRepository.findByEmail(email).get();

        if ((comment.getUsername().equals(user.getUsername())) || (user.getRoles().contains("ADMIN")) || (user.getRoles().contains("MANAGER"))) {
            commentRepository.delete(comment);
            return true;
        } else
              return false;
    }
}
