package RGR.photogallery.domain;

import javax.persistence.*;

@Entity
@Table(name = "commentImage")
public class CommentImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long imageId;

    private Long commentId;


    public CommentImage() {}
    public CommentImage(Long id, Long imageId, Long commentId) {
        this.id = id;
        this.imageId = imageId;
        this.commentId = commentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }
}
