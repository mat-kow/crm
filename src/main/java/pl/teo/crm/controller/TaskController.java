package pl.teo.crm.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.teo.crm.app.exception.ApiBadRequestException;
import pl.teo.crm.model.Task;
import pl.teo.crm.model.dto.TaskCreationDto;
import pl.teo.crm.model.dto.TaskDto;
import pl.teo.crm.service.TaskService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@Slf4j
public class TaskController {

    private final TaskService taskService;

    @PostMapping("")
    public void createNewTask(@Valid @RequestBody TaskCreationDto dto, Principal principal) {
        taskService.createTask(dto, principal);
    }

    @GetMapping("/project/{projectId}")
    public List<TaskDto> getByProjectId(@PathVariable int projectId) {
        return taskService.getByProjectId(projectId);
    }

    @GetMapping("/{id}")
    public TaskDto getTask(@PathVariable int id) {
        return taskService.getTask(id);
    }

    @PutMapping("/{taskId}")
    public TaskDto updateTask(@PathVariable int taskId, @Valid @RequestBody Task task, Principal principal) {
        if (taskId != task.getId()) {
            throw new ApiBadRequestException("id from URL don't match entity id");
        }
        return taskService.update(task, principal);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable int taskId) {
        taskService.delete(taskId);
    }

}
