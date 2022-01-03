package pl.teo.crm.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.teo.crm.model.Task;
import pl.teo.crm.model.dto.TaskDto;
import pl.teo.crm.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class TaskController {

    private final TaskService taskService;

    @PostMapping("")
    public void createNewTask(@RequestBody TaskDto dto) {
        taskService.createTask(dto);
    }

    @GetMapping("/project/{projectId}")
    public List<Task> getByProjectId(@PathVariable int projectId) {
        return taskService.getByProjectId(projectId);
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable int id) {
        return taskService.getTask(id);
    }

    @PutMapping("/{taskId}")
    public Task updateTask(@PathVariable int taskId, @RequestBody Task task) {
        if (taskId != task.getId()) {
            throw new RuntimeException(); //todo exception
        }
        return taskService.update(task);
    }

}
