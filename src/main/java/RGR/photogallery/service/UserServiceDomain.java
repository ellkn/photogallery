package RGR.photogallery.service;

import RGR.photogallery.domain.User;
import RGR.photogallery.mail.MailSender;
import RGR.photogallery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceDomain implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private MailSender mailSender;

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

        String message = String.format("Пожалуйста, для активации аккаунта перейдите по ссылке: http://localhost:8080/activate/%s",
                user.getUsername());
        mailSender.send(user.getEmail(), "Activation Code", message);

        return true;
    }
    @Override
    public boolean activateUser(String username) {
        if (userRepository.findByUsername(username) != null) {
            User user = userRepository.findByUsername(username);
            user.setEnabled(true);
            userRepository.save(user);
            return true;
        } else return false;
    }

//    @Override
//    public UserService loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username);
//
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found");
//        }
//
//        return user;
//    }

}
