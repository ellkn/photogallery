package RGR.photogallery.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class UserRegistrationForm {
    @Email
    private String email;

    @NotBlank
    private String userName;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String date;

    @NotBlank
    @Size(min = 5)
    private String password;

    @NotNull(message = "Password not match")
    private String passwordConfim;

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPasswordConfim() {
        return passwordConfim;
    }
    public void setPasswordConfim(String passwordConfim) {
        this.passwordConfim = passwordConfim;

        if (!this.password.equals(this.passwordConfim)) {
            this.passwordConfim = null;
        }
    }
}
