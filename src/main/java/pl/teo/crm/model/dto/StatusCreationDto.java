package pl.teo.crm.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter @Setter
public class StatusCreationDto {
    @NotBlank(message = "notBlank") @Size(min = 3, max = 10, message = "size")
    private String name;
    private int sortingValue;
}
