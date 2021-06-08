package RGR.photogallery.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;
    @NotBlank
    private String text;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private Image image;

    public Comment() {
    }

    public Comment(Long id, User author, @NotBlank String text, Image image) {
        this.id = id;
        this.author = author;
        this.text = text;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }
}
