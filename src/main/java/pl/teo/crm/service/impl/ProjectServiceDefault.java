package pl.teo.crm.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.teo.crm.model.Project;
import pl.teo.crm.model.dto.ProjectDto;
import pl.teo.crm.model.dto.ProjectFormDto;
import pl.teo.crm.model.repository.ProjectRepo;
import pl.teo.crm.model.repository.UserRepo;
import pl.teo.crm.service.ProjectService;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class ProjectServiceDefault implements ProjectService {
    private final ModelMapper mapper;
    private final ProjectRepo projectRepo;
    private final UserRepo userRepo;

    @Override
    @Transactional
    public Project createNewProject(ProjectFormDto dto) {
        Project project = mapper.map(dto, Project.class);
        project.setSlug(generateSlug(project.getName()));
        project.setActive(true);
        return projectRepo.save(project);
    }

    @Override
    @Transactional
    public boolean activateProject(int projectId) {
        Project project = projectRepo.findById(projectId).orElseThrow(RuntimeException::new); //todo custom exception
        if (project.isActive()) {
            return false;
        }
        project.setActive(true);
        projectRepo.save(project);
        return true;
    }
    @Override
    @Transactional
    public boolean deactivateProject(int projectId) {
        Project project = projectRepo.findById(projectId).orElseThrow(RuntimeException::new); //todo custom exception
        if (!project.isActive()) {
            return false;
        }
        project.setActive(false);
        projectRepo.save(project);
        return true;
    }

    @Override
    @Transactional
    public Project addUsers(int projectId, List<Integer> usersIds) {
        Project project = projectRepo.findById(projectId).orElseThrow(RuntimeException::new); //todo custom exception
        usersIds.forEach(userId -> project.addUser(userRepo.getById(userId))); //todo if user don't exist
        return projectRepo.save(project);
    }

    @Override
    @Transactional
    public void deleteUsers(int projectId, List<Integer> usersIds) {
        Project project = projectRepo.findById(projectId).orElseThrow(RuntimeException::new); //todo custom exception
        usersIds.forEach(userId -> project.removeUser(userRepo.getById(userId))); //todo if user don't exist
        projectRepo.save(project);
    }

    @Override
    public ProjectDto getProjectById(int projectId) {
        Project project = projectRepo.getById(projectId);
        ProjectDto dto = mapper.map(project, ProjectDto.class);
        dto.setCreatedAt(project.getCreatedAt().toLocalDateTime());
        return dto;
    }

    @Override
    @Transactional
    public Project update(Project project) {
        return projectRepo.save(project);
    }

    @Override
    public List<Project> getAll() {
        return projectRepo.findAll();
    }

    private String generateSlug(String input) {
        return StringUtils.stripAccents(input)
                .replaceAll("\\s", "-")
                .toLowerCase(Locale.ROOT)
                .replaceAll("[,.!?]", "");

    }
}
