package pl.teo.crm.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity (name = "users")
@Getter @Setter
public class User {
    @Id @GeneratedValue
    private int id;
    private String username;
    private String firstname;
    private String lastname;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

}
