package RGR.photogallery.service;

import RGR.photogallery.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceDomain implements CommentService{
    @Autowired
    CommentRepository commentRepository;

}
