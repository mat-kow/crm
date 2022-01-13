package pl.teo.crm.model.dto;

import lombok.Getter;
import lombok.Setter;
import pl.teo.crm.model.Priority;
import pl.teo.crm.model.Status;

import java.sql.Timestamp;

@Getter @Setter
public class TaskDto {
    private int id;
    private String topic;
    private String description;
    private Timestamp createdAt;
    private ProjectDto project;
    private Status status;
    private Priority priority;
    private UserDto user;

}
