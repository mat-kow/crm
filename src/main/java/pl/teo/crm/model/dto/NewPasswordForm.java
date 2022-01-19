package pl.teo.crm.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter @Setter
public class NewPasswordForm {
    @Size(min = 6, max = 20, message = "size") @NotBlank
    private String newPass;
    private String oldPass;
}
