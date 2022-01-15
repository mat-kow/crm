package pl.teo.crm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity (name = "priorities")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Priority {
    @Id @GeneratedValue
    private int id;
    @NotBlank(message = "notBlank") @Size(min = 3, max = 10, message = "size")
    private String name;
    private boolean active;
    private int sortValue;
}
