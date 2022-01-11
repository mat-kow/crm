package pl.teo.crm.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.teo.crm.app.exception.ApiBadRequestException;
import pl.teo.crm.app.exception.ApiNotFoundException;
import pl.teo.crm.model.*;
import pl.teo.crm.model.dto.TaskDto;
import pl.teo.crm.model.repository.*;
import pl.teo.crm.service.TaskService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceDefault implements TaskService {

    private final TaskRepo taskRepo;
    private final ProjectRepo projectRepo;
    private final StatusRepo statusRepo;
    private final PriorityRepo priorityRepo;
    private final UserRepo userRepo;

    @Transactional
    @Override
    public void createTask(TaskDto dto) {
        Task task = new Task();
        task.setTopic(dto.getTopic());
        task.setDescription(dto.getDescription());
        Project project = projectRepo.findById(dto.getProjectId()).orElseThrow(ApiNotFoundException::new);
        task.setProject(project);
        task.setStatus(statusRepo.findAll().get(0));
        Priority priority = priorityRepo.findByName(dto.getPriorityName()).orElseThrow(ApiNotFoundException::new);
        task.setPriority(priority);
        User user = userRepo.findById(dto.getUserId()).orElseThrow(ApiNotFoundException::new);
        if (!project.getUsers().contains(user)) {
            throw new ApiBadRequestException(String.format("user %s is not assigned to this project", user.getUsername()));
        }
        task.setUser(user);
        taskRepo.save(task);
    }

    @Override
    public List<Task> getByProjectId(int projectId) {
        return taskRepo.findAllByProjectId(projectId);
    }

    @Override
    public Task getTask(int id) {
        return taskRepo.findById(id).orElseThrow(ApiNotFoundException::new);
    }

    @Override
    public Task update(Task task) {
        return taskRepo.save(task);
    }
}
