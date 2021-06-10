package RGR.photogallery.service;

import RGR.photogallery.domain.Album;

import java.util.List;

public interface AlbumService {
    boolean addAlbum(Long userId, String title);
    List<Album> allAlbumByUser(Long userId);
    boolean deleteAlbum(Long albumId, String username);
}
