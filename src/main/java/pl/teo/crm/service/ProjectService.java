package pl.teo.crm.service;

import pl.teo.crm.model.Project;
import pl.teo.crm.model.dto.ProjectDto;
import pl.teo.crm.model.dto.ProjectCreationDto;

import java.security.Principal;
import java.util.List;

public interface ProjectService {
    Project createNewProject(ProjectCreationDto dto, Principal principal);

    ProjectDto getProjectById(int projectId);

    Project update(Project project);

    List<Project> getAll();
}
