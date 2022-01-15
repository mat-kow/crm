package pl.teo.crm.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity (name = "tasks")
@Getter @Setter
public class Task {
    @Id @GeneratedValue
    private int id;
    @NotBlank(message = "notBlank") @Size(min = 3, max = 20, message = "size")
    private String topic;
    @Size(max = 200, message = "size")
    private String description;
    @CreationTimestamp
    private Timestamp createdAt;
    @ManyToOne
    private Project project;
    @ManyToOne
    private Status status;
    @ManyToOne
    private Priority priority;
    @ManyToOne
    private User user;
}
