package RGR.photogallery.service;

import RGR.photogallery.domain.User;
import RGR.photogallery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceDomain implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getList() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);

        return users;
    }

    @Override
    public boolean isUserWithEmailExist(String email) {
        return userRepository.countByEmail(email) != 0 ? true : false;
    }


    @Override
    public boolean addUser(String username, String firstName, String lastName, String email, String date, String password){
        User user = new User();

        user.setUsername(username);
        user.setFirstname(firstName);
        user.setLastname(lastName);
        user.setEmail(email);
        user.setDate(date);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setEnabled(false);
        user.setRoles("USER");

        userRepository.save(user);

        return true;
    }
//    @Override
//    public boolean activateUser(String code) {
//        User user = userRepository.findByActivationCode(code);
//
//        if (user == null) {
//            return false;
//        }
//        user.setActivationCode(null);
//        user.setActive(true);
//        userRepository.save(user);
//
//        return true;
//    }
}
