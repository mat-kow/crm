package pl.teo.crm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.teo.crm.model.Project;
import pl.teo.crm.model.dto.ProjectDto;
import pl.teo.crm.service.ProjectService;

import java.util.List;

@RestController
@RequestMapping("/api/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping("")
    public String createProject(@RequestBody ProjectDto dto) {
        return projectService.createNewProject(dto);
    }

    @PutMapping("/{projectId}/activate")
    public boolean activateProject(@PathVariable int projectId) {
        return projectService.activateProject(projectId);
    }

    @PutMapping("/{projectId}/deactivate")
    public boolean deactivateProject(@PathVariable int projectId) {
        return projectService.deactivateProject(projectId);
    }

    @PostMapping("/{projectId}/users")
    public void addUsers(@PathVariable int projectId, @RequestBody List<Integer> users) {
        projectService.addUsers(projectId, users); ;
    }

    @DeleteMapping("/{projectId}/users")
    public void removeUsers(@PathVariable int projectId, @RequestBody List<Integer> users) {
        projectService.deleteUsers(projectId, users);
    }

    @GetMapping("/{projectId}")
    public Project getProject(@PathVariable int projectId) {
        return projectService.getProjectById(projectId);
    }


}
