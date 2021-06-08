package RGR.photogallery.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;
    private String title;
    private Date uploadDate;
    private Date deletedOn;
  //  private User deletedBy;
    private String fullPath;
    private String fileName;
    private String tags;
    private BigDecimal size;
   // private List<Comment> comments;
    //private Album album;

    public Image() {
    }


}
