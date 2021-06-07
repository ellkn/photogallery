package RGR.photogallery.domain;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.*;
@Entity
@Table(name = "user")
public class User implements Serializable {
    private static final long serialVersionUID = 6216344084865363418L;

    @javax.persistence.Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long userId;
    private String email;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private Date dateOfBirth;
    private boolean enabled;
    private String token;
    private String roles;
    private Integer imageId;

    public User() {
    }

    public User(Long userId, String email, String firstName, String lastName, String userName, String password, Date dateOfBirth, boolean enabled, String token, String roles, Integer imageId) {
        this.userId = userId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.enabled = enabled;
        this.token = token;
        this.roles = roles;
        this.imageId = imageId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Set<Role> getUserRoles() {
        Set<Role> userRoles = new HashSet<>();

        if (roles != null && roles.length() > 0) {

            String[] rolesArr = roles.split(",");
            for (String role : rolesArr) {
                userRoles.add(Role.valueOf(role));
            }
        }

        return userRoles;
    }
    public String getHighLevelRole() {

        List<String> allRoles = new ArrayList<>();

        for (Role role : this.getUserRoles()) {
            allRoles.add(role.toString());
        }

        if (allRoles.contains(Role.ADMIN.toString())) {
            return Role.ADMIN.toString();
        } else if(allRoles.contains(Role.MANAGER.toString())) {
            return Role.MANAGER.toString();
        } else {
            return Role.USER.toString();
        }
    }
    public List<String> getRolesList() {
        List<String> list = new ArrayList<>();

        this.getUserRoles().toArray();

        for (Role role : this.getUserRoles()) {
            list.add(role.toString());
        }

        return list;
    }
    public void addRole(Role role) {
        Set<Role> roleSet = this.getUserRoles();
        roleSet.add(role);

        this.roles = convertRoleSetToString(roleSet);
    }

    public void removeRole(Role role) {
        Set<Role> roleSet = this.getUserRoles();
        roleSet.remove(role);

        this.roles = convertRoleSetToString(roleSet);
    }

    private String convertRoleSetToString(Set<Role> roleSet) {
        List<String> roleArr = new ArrayList<>(roleSet.size());
        roleSet.forEach(c -> roleArr.add(c.toString()));

        return String.join(",", roleArr);
    }

    public String getFullName() {
        return this.userName;
    }


    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", imageId=" + imageId +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (obj == this)
            return true;

        if (!(obj instanceof User))
            return false;

        User user = (User)obj;

        if (user.hashCode() == this.hashCode())
            return true;

        return false;
    }
    @Override
    public int hashCode() {
        return Objects.hash(userId, email, password);
    }
}
