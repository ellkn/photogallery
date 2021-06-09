package RGR.photogallery.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import RGR.photogallery.domain.User;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "album")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    @NotBlank
    private String title;
    private Boolean isShared;

    public Album() {
    }

    public Album(Long id, Long userId, String title, Boolean isShared) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.isShared = isShared;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
