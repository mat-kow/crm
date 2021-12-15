package pl.teo.crm.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.teo.crm.model.*;
import pl.teo.crm.model.dto.TaskDto;
import pl.teo.crm.model.repository.*;
import pl.teo.crm.service.TaskService;

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
        Project project = projectRepo.findById(dto.getProjectId()).orElseThrow(RuntimeException::new); //todo custom exception
        task.setProject(project);
        Status status = statusRepo.findById(dto.getStatusId()).orElseThrow(RuntimeException::new); //todo custom exception
        task.setStatus(status);
        Priority priority = priorityRepo.findById(dto.getPriorityName()).orElseThrow(RuntimeException::new); //todo custom exception
        task.setPriority(priority);
        User user = userRepo.findById(dto.getUserId()).orElseThrow(RuntimeException::new); //todo exception
        if (!project.getUsers().contains(user)) {
            throw new IllegalArgumentException(); //todo maybe better exception
        }
        task.setUser(user);
        taskRepo.save(task);
    }
}
