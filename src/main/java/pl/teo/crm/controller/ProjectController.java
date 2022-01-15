package pl.teo.crm.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.teo.crm.app.exception.ApiBadRequestException;
import pl.teo.crm.model.Project;
import pl.teo.crm.model.dto.ProjectDto;
import pl.teo.crm.model.dto.ProjectCreationDto;
import pl.teo.crm.service.ProjectService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
@Slf4j
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping("")
    public Project createProject(@Valid @RequestBody ProjectCreationDto dto, Principal principal) {
        log.info(String.format("Project: <%s> is creating, description: <%s>, site: <%s>",
                dto.getName(), dto.getDescription().substring(0, Math.min(dto.getDescription().length(), 10)), dto.getSite()));
        return projectService.createNewProject(dto, principal);
    }

    @GetMapping("/{projectId}")
    public ProjectDto getProject(@PathVariable int projectId) {
        log.info("Fetching project, id: {}", projectId);

        return projectService.getProjectById(projectId);
    }

    @PutMapping("/{projectId}")
    public Project updateProject(@PathVariable int projectId, @Valid @RequestBody Project project) {
        if (projectId == project.getId()) {
            log.info("update project {}", project.getName());
            return projectService.update(project);
        }
        throw new ApiBadRequestException("id from URL don't match entity id");
    }

    @GetMapping("")
    public List<Project> getAll() {
        return projectService.getAll();
    }


}
