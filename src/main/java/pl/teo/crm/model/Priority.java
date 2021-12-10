package pl.teo.crm.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name = "priorities")
@Getter @Setter
public class Priority {
    @Id @GeneratedValue
    private int id;
    private String name;
    private boolean active;
}
