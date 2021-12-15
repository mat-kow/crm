package pl.teo.crm.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity (name = "priorities")
@Getter @Setter
public class Priority {
    @Id
    private String name;
    private boolean active;
}
