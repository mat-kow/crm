package pl.teo.crm.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name = "users")
@Getter @Setter
public class User {
    @Id @GeneratedValue
    private int id;
    private String username;
    private String firstname;
    private String lastname;
    private String password;
}
