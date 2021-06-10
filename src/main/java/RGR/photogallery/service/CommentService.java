package RGR.photogallery.service;

public interface CommentService {
    boolean addComment(String text, String username, Long imageId);
    boolean deleteComment(Long commentId, String email);
}
