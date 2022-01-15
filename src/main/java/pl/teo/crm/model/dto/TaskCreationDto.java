package pl.teo.crm.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter @Setter
public class TaskCreationDto {
    @NotBlank(message = "notBlank") @Size(min = 3, max = 20, message = "size")
    private String topic;

    @Size(max = 200, message = "size")
    private String description;
    private int projectId;
    private String priorityName;
    private int userId;

}
