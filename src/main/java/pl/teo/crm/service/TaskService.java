package pl.teo.crm.service;

import pl.teo.crm.model.Task;
import pl.teo.crm.model.dto.TaskDto;

import java.util.List;

public interface TaskService {
    void createTask(TaskDto dto);
    List<Task> getByProjectId(int projectId);
    Task getTask(int id);

    Task update(Task task);
}
