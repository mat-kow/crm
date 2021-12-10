package pl.teo.crm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "roles")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Role {
    @Id
    private String name;
}
