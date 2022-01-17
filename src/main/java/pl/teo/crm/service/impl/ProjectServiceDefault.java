package pl.teo.crm.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.teo.crm.app.exception.ApiNotFoundException;
import pl.teo.crm.model.*;
import pl.teo.crm.model.dto.ProjectDto;
import pl.teo.crm.model.dto.ProjectCreationDto;
import pl.teo.crm.model.repository.ActivityRepo;
import pl.teo.crm.model.repository.ProjectRepo;
import pl.teo.crm.model.repository.UserRepo;
import pl.teo.crm.service.ProjectService;
import pl.teo.crm.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class ProjectServiceDefault implements ProjectService {
    private final ModelMapper mapper;
    private final ProjectRepo projectRepo;
    private final ActivityRepo activityRepo;
    private final UserService userService;

    @Override
    @Transactional
    public Project createNewProject(ProjectCreationDto dto, Principal principal) {
        Project project = mapper.map(dto, Project.class);
        project.setSlug(generateSlug(project.getName()));
        project.setActive(true);
        User currentUser = userService.getCurrentUser(principal);
        project.addUser(currentUser);
        projectRepo.save(project);
        Activity activity = new Activity(principal.getName(), ActivitySubject.PROJECT, project.getId(),
                ActivityAction.CREATION, project.getId());
        activityRepo.save(activity);
        return project;
    }

    @Override
    public ProjectDto getProjectById(int projectId) {
        Project project = projectRepo.findById(projectId).orElseThrow(ApiNotFoundException::new);
        ProjectDto dto = mapper.map(project, ProjectDto.class);
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
