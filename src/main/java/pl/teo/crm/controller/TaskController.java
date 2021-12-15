package pl.teo.crm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.teo.crm.model.dto.TaskDto;
import pl.teo.crm.service.TaskService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/task")
public class TaskController {

    private final TaskService taskService;

    @PostMapping("")
    public void createNewTask(@RequestBody TaskDto dto) {
        taskService.createTask(dto);
    }
}
