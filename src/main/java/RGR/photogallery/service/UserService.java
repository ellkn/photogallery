package RGR.photogallery.service;

import RGR.photogallery.domain.User;

import java.util.List;

public interface UserService {
    List<User> getList();
    boolean isUserWithEmailExist(String email);
}
