package RGR.photogallery.service;

import RGR.photogallery.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumServiceDomain implements AlbumService{
    @Autowired
    AlbumRepository albumRepository;
}
