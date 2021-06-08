package RGR.photogallery.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;

@Entity
public class Friend {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne
    @JoinColumn(name="userfid", nullable=false)
    private User userf;

    @ManyToOne
    @JoinColumn(name="usersid", nullable=false)
    private User users;

    @Column(name="friendship")
    private boolean friendship;

    public Friend() {
    }

    public Friend(int id, User userf, User users, boolean friendship) {
        this.id = id;
        this.userf = userf;
        this.users = users;
        this.friendship = friendship;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUserf() {
        return userf;
    }

    public void setUserf(User userf) {
        this.userf = userf;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public boolean isFriendship() {
        return friendship;
    }

    public void setFriendship(boolean friendship) {
        this.friendship = friendship;
    }
}
