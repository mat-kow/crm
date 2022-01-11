package pl.teo.crm.service;

import pl.teo.crm.model.Project;
import pl.teo.crm.model.dto.ProjectDto;
import pl.teo.crm.model.dto.ProjectFormDto;

import java.security.Principal;
import java.util.List;

public interface ProjectService {
    Project createNewProject(ProjectFormDto dto, Principal principal);
    boolean activateProject(int projectId);
    boolean deactivateProject(int projectId);
    Project addUsers(int projectId, List<Integer> usersIds);
    void deleteUsers(int projectId, List<Integer> usersIds);

    ProjectDto getProjectById(int projectId);

    Project update(Project project);

    List<Project> getAll();
}
