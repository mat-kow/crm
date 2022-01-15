package pl.teo.crm.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UserCreationDto {
    @NotBlank(message = "notBlank") @Size(min = 3, max = 10, message = "size")
    private String username;

    @Size(max = 20, message = "size")
    private String firstname;

    @Size(max = 20, message = "size")
    private String lastname;

    @Size(min = 6, max = 20, message = "size") @NotBlank
    private String password;
}
