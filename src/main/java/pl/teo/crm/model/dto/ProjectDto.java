package pl.teo.crm.model.dto;

import lombok.Getter;
import lombok.Setter;
import pl.teo.crm.model.User;

import java.time.LocalDateTime;
import java.util.Set;

@Getter @Setter
public class ProjectDto {
    private int id;
    private String name;
    private String description;
    private String slug;
    private String site;
    private Set<User> users;
    private boolean active;
    private LocalDateTime createdAt;

}
