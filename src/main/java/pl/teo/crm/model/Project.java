package pl.teo.crm.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity (name = "projects")
@Getter @Setter
public class Project {
    @Id @GeneratedValue
    private int id;
    private String name;
    private String description;
    private String slug;
    private String site;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<User> users;
    private boolean active;
    @CreationTimestamp
    private Timestamp createdAt;


    public void addUser(User user) {
        if (users == null) {
            users = new HashSet<>();
        }
        users.add(user);
    }

    public void removeUser(User user) {
        if (!users.isEmpty()) {
            users.remove(user);
        }
    }

}
