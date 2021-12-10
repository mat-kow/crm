package pl.teo.crm.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity @Table(name = "projects")
@Getter @Setter
public class Project {
    @Id @GeneratedValue
    private int id;
    private String name;
    private String description;
    private String slug;
    private String password;
    private String site;
    @OneToMany
    private List<User> users;
    boolean active;
}
