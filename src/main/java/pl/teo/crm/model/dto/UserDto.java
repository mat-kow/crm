package pl.teo.crm.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UserDto {
    private int id;
    private String username;

    @Size(max = 20, message = "size")
    private String firstname;

    @Size(max = 20, message = "size")
    private String lastname;
}
