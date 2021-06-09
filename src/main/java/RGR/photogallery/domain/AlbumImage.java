package RGR.photogallery.domain;

import javax.persistence.*;

@Entity
@Table(name = "album_image")
public class AlbumImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long imageId;

    private Long albumId;

    public AlbumImage() {
    }

    public AlbumImage(Long id, Long imageId, Long albumId) {
        this.id = id;
        this.imageId = imageId;
        this.albumId = albumId;
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

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }
}
