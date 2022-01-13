package pl.teo.crm.service;

import pl.teo.crm.model.Task;
import pl.teo.crm.model.dto.TaskCreationDto;
import pl.teo.crm.model.dto.TaskDto;

import java.util.List;

public interface TaskService {
    void createTask(TaskCreationDto dto);
    List<TaskDto> getByProjectId(int projectId);
    TaskDto getTask(int id);

    TaskDto update(Task task);
}
