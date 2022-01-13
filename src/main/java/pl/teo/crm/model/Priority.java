package pl.teo.crm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity (name = "priorities")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Priority {
    @Id @GeneratedValue
    private int id;
    private String name;
    private boolean active;
    private int sortValue;
}
