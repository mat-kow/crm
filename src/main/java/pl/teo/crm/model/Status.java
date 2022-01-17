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

@Entity (name = "statuses")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Status {
    @Id @GeneratedValue
    private int id;
    @NotBlank(message = "notBlank") @Size(min = 3, max = 20, message = "size")
    private String name;
    private int sortingValue;
    private boolean active;
}
