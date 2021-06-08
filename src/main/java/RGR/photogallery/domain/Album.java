package RGR.photogallery.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import RGR.photogallery.domain.User;

import java.util.List;
import java.util.Set;

@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @NotBlank
    private String title;
    private Boolean isShared;
    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Image> images;

    public Album() {
        }

    public Album(Long id, User user, String title, Boolean isShared) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.isShared = isShared;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getShared() {
        return isShared;
    }

    public void setShared(Boolean shared) {
        isShared = shared;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public String getAuthorName() {
        return user != null ? user.getUsername() : "<none>";
    }
}
