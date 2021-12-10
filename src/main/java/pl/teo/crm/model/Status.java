package pl.teo.crm.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity (name = "statuses")
@Getter @Setter
public class Status {
    @Id @GeneratedValue
    private int id;
    private String name;
    private int sortingValue;
    private boolean active;
}
