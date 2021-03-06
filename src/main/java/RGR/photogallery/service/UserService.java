package RGR.photogallery.service;

import RGR.photogallery.domain.User;

import java.util.List;

public interface UserService {
    List<User> getList();
    boolean activateUser(Long id);
    boolean isUserWithEmailExist(String email);
    boolean changeAvatar(Long id, String image);
    boolean addUser(String username, String firstName, String lastName, String email, String date, String password);
    boolean deleteUser(Long userId, String emailAdmin);
    boolean changeRole(Long userId, String EmailAdmin);
}
