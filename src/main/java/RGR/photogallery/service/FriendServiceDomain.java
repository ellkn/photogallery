package RGR.photogallery.service;

import RGR.photogallery.repository.FriendRepository;
import RGR.photogallery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendServiceDomain implements FriendService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    FriendRepository friendRepository;
}
