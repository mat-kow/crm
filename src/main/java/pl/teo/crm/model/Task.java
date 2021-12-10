package pl.teo.crm.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity @Table(name = "tasks")
@Getter @Setter
public class Task {
    @Id @GeneratedValue
    private int id;
    private String topic;
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
