package pl.teo.crm.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Set;

@Getter @Setter
public class ProjectDto {
    private int id;
    private String name;
    private String description;
    private String slug;
    private String site;
    private Set<UserDto> users;
    private boolean active;
    private Timestamp createdAt;

}
