package pl.teo.crm.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity (name = "projects")
@Getter @Setter
public class Project {
    @Id @GeneratedValue
    private int id;
    @NotBlank(message = "notBlank") @Size(min = 3, max = 50, message = "size")
    private String name;
    @NotNull
    @Size(max = 200, message = "size")
    private String description;
    private String slug;
    @Pattern(regexp = "((https?:\\/\\/)?[\\w\\d]+([.\\w\\d]+)*(:\\d+)?(\\/[\\w\\d]+)*)|()", message = "pattern")// todo fix regex
    private String site;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<User> users;
    private boolean active;
    @CreationTimestamp
    private Timestamp createdAt;

    @OneToMany(mappedBy = "project", cascade = CascadeType.REMOVE)
    private Set<Task> tasks;


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
