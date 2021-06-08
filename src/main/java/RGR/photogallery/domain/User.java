package RGR.photogallery.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "user")
public class User implements Serializable {
    private static final long serialVersionUID = 6216344084865363418L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String email;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String date;
    private boolean enabled;
    private String role;
    private Integer imageid;

    public User() {
    }

    public User(Long id, String email, String firstname, String lastname, String username, String password, String date, boolean enabled, String role, Integer imageid) {
        this.id = id;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.date = date;
        this.enabled = enabled;
        this.role = role;
        this.imageid = imageid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getRoles() {
        return role;
    }

    public void setRoles(String role) {
        this.role = role;
    }

    public Set<Role> getUserRoles() {
        Set<Role> userRoles = new HashSet<>();

        if (role != null && role.length() > 0) {

            String[] roleArr = role.split(",");
            for (String role : roleArr) {
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

        this.role = convertRoleSetToString(roleSet);
    }

    public void removeRole(Role role) {
        Set<Role> roleSet = this.getUserRoles();
        roleSet.remove(role);

        this.role = convertRoleSetToString(roleSet);
    }

    private String convertRoleSetToString(Set<Role> roleSet) {
        List<String> roleArr = new ArrayList<>(roleSet.size());
        roleSet.forEach(c -> roleArr.add(c.toString()));

        return String.join(",", roleArr);
    }

    public String getFullname() {
        return this.username;
    }


    public Integer getImageId() {
        return imageid;
    }

    public void setImageId(Integer imageid) {
        this.imageid = imageid;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", date=" + date +
                ", imageid=" + imageid +
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
        return Objects.hash(id, email, password);
    }
}
