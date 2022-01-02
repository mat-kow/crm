package pl.teo.crm.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.teo.crm.model.dto.TaskDto;
import pl.teo.crm.service.TaskService;

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
}
