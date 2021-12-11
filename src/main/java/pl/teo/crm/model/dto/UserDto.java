package pl.teo.crm.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UserDto {
    private String username;
    private String firstname;
    private String lastname;
    private String password;
}
