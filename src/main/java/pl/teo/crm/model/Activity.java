package pl.teo.crm.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "activities")
@Getter @Setter @NoArgsConstructor
public class Activity {
    @Id @GeneratedValue
    private int id;
    private String username;
    @Enumerated(EnumType.STRING)
    private ActivitySubject subject;
    private int subjectId;
    @Enumerated(EnumType.STRING)
    private ActivityAction action;
    private int projectId;
    @CreationTimestamp
    private Timestamp created;
    private String projectName;
    private String taskTopic;

    public Activity(String username, ActivitySubject subject, int subjectId, ActivityAction action, int projectId) {
        this.username = username;
        this.subject = subject;
        this.subjectId = subjectId;
        this.action = action;
        this.projectId = projectId;
    }

}
