package pl.teo.crm.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.teo.crm.model.Project;
import pl.teo.crm.model.dto.ProjectDto;
import pl.teo.crm.model.dto.ProjectFormDto;
import pl.teo.crm.service.ProjectService;

import java.util.List;

@RestController
@RequestMapping("/api/project")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping("")
    public Project createProject(@RequestBody ProjectFormDto dto) {
        log.info(String.format("Project: <%s> is creating, description: <%s>, site: <%s>",
                dto.getName(), dto.getDescription().substring(0, Math.min(dto.getDescription().length(), 10)), dto.getSite()));
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
    public Project addUsers(@PathVariable int projectId, @RequestBody List<Integer> users) {
        return projectService.addUsers(projectId, users);    }

    @DeleteMapping("/{projectId}/users")
    public void removeUsers(@PathVariable int projectId, @RequestBody List<Integer> users) {
        projectService.deleteUsers(projectId, users);
    }

    @GetMapping("/{projectId}")
    public ProjectDto getProject(@PathVariable int projectId) {
        log.info(String.format("Fetching project, id: <%d>", projectId));

        return projectService.getProjectById(projectId);
    }

    @PutMapping("/{projectId}")
    public Project updateProject(@PathVariable int projectId, @RequestBody Project project) {
        if (projectId == project.getId()) {
            return projectService.update(project);
        }
        throw new IllegalArgumentException(); //todo exception
    }

    @GetMapping("")
    public List<Project> getAll() {
        return projectService.getAll();
    }


}
