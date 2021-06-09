package RGR.photogallery.service;

import RGR.photogallery.domain.User;

import java.util.List;

public interface UserService {
    List<User> getList();
    boolean activateUser(String username);
    boolean isUserWithEmailExist(String email);
    boolean addUser(String username, String firstName, String lastName, String email, String date, String password);
//    boolean activateUser(String code);
}
