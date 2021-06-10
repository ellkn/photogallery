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
import java.util.Optional;

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
        user.setEnabled(true);
        user.setRoles("USER");
        user.setImage("default_avatar.jpg");

        userRepository.save(user);

        String message = String.format("Пожалуйста, для активации аккаунта перейдите по ссылке: http://localhost:8080/activate/%s",
                user.getId());
        mailSender.send(user.getEmail(), "Activation Code", message);

        return true;
    }
    @Override
    public boolean activateUser(Long id) {
        User user = userRepository.findById(id).get();
        if (user != null) {
            user.setEnabled(true);
            userRepository.save(user);
            return true;
        } else return false;
    }


    @Override
    public boolean changeAvatar(Long id, String image) {
        if (image != null) {
            User user = userRepository.findById(id).get();
            user.setImage(image);
            userRepository.save(user);
            return true;
        } else return false;
    }

}
