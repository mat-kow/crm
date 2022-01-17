package pl.teo.crm.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.teo.crm.app.exception.ApiBadRequestException;
import pl.teo.crm.app.exception.ApiNotFoundException;
import pl.teo.crm.model.*;
import pl.teo.crm.model.dto.TaskCreationDto;
import pl.teo.crm.model.dto.TaskDto;
import pl.teo.crm.model.repository.*;
import pl.teo.crm.service.TaskService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceDefault implements TaskService {

    private final TaskRepo taskRepo;
    private final ProjectRepo projectRepo;
    private final StatusRepo statusRepo;
    private final PriorityRepo priorityRepo;
    private final UserRepo userRepo;
    private final ModelMapper mapper;
    private final ActivityRepo activityRepo;

    @Transactional
    @Override
    public void createTask(TaskCreationDto dto, Principal principal) {
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
        Activity activity = new Activity(principal.getName(), ActivitySubject.TASK, task.getId(),
                ActivityAction.CREATION, task.getProject().getId());
        activityRepo.save(activity);
    }

    @Override
    public List<TaskDto> getByProjectId(int projectId) {
        return taskRepo.findAllByProjectId(projectId).stream()
                .map(task -> mapper.map(task, TaskDto.class)).collect(Collectors.toList());
    }

    @Override
    public TaskDto getTask(int id) {
        Task task = taskRepo.findById(id).orElseThrow(ApiNotFoundException::new);
        return mapper.map(task, TaskDto.class);
    }

    @Override
    @Transactional
    public TaskDto update(Task task, Principal principal) {
        String oldStatus = taskRepo.getStatusNameByTaskId(task.getId());
        taskRepo.save(task);
        if (!task.getStatus().getName().equals(oldStatus)) {
            Activity activity = new Activity(principal.getName(), ActivitySubject.TASK, task.getId(),
                    ActivityAction.STATUS_CHANGE, task.getProject().getId());
            activityRepo.save(activity);
        }
        return mapper.map(task, TaskDto.class);
    }
}
