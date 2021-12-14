package pl.teo.crm.service;

import pl.teo.crm.model.Project;
import pl.teo.crm.model.dto.ProjectDto;

import java.util.List;

public interface ProjectService {
    String createNewProject(ProjectDto dto);
    boolean activateProject(int projectId);
    boolean deactivateProject(int projectId);
    void addUsers(int projectId, List<Integer> usersIds);
    void deleteUsers(int projectId, List<Integer> usersIds);

    Project getProjectById(int projectId);
}
