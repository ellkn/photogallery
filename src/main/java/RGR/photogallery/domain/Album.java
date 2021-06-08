package RGR.photogallery.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import RGR.photogallery.domain.User;

import java.util.List;
import java.util.Set;

@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long albumId;
   // private Set<User> user;
    private String title;
    private String description;
    private Boolean isShared;
  //  private Set<Image> images;

    public Album() {
        }

}
