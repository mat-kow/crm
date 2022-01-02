package pl.teo.crm.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TaskDto {
    private String topic;
    private String description;
    private int projectId;
    private String priorityName;
    private int userId;

}
