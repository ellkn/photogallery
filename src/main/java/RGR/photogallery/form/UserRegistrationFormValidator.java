package RGR.photogallery.form;

import RGR.photogallery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserRegistrationFormValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserRegistrationForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserRegistrationForm form = (UserRegistrationForm)target;

        System.out.println("validation");
        if(userService.isUserWithEmailExist(form.getEmail())) {

            errors.rejectValue("email", "", "User with email already registered");
        }

    }
}
