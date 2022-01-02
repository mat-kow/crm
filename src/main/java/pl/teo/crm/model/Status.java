package pl.teo.crm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity (name = "statuses")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Status {
    @Id @GeneratedValue
    private int id;
    private String name;
    private int sortingValue;
    private boolean active;
}
