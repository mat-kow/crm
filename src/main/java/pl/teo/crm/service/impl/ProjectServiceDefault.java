package pl.teo.crm.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.teo.crm.app.exception.ApiNotFoundException;
import pl.teo.crm.model.Project;
import pl.teo.crm.model.User;
import pl.teo.crm.model.dto.ProjectDto;
import pl.teo.crm.model.dto.ProjectCreationDto;
import pl.teo.crm.model.repository.ProjectRepo;
import pl.teo.crm.model.repository.UserRepo;
import pl.teo.crm.service.ProjectService;

import java.security.Principal;
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
    public Project createNewProject(ProjectCreationDto dto, Principal principal) {
        Project project = mapper.map(dto, Project.class);
        project.setSlug(generateSlug(project.getName()));
        project.setActive(true);
        project.addUser(getCurrentUser(principal));
        return projectRepo.save(project);
    }

    @Override
    public ProjectDto getProjectById(int projectId) {
        Project project = projectRepo.findById(projectId).orElseThrow(ApiNotFoundException::new);
        ProjectDto dto = mapper.map(project, ProjectDto.class);
//        dto.setCreatedAt(project.getCreatedAt().toLocalDateTime());
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

    private User getCurrentUser(Principal principal) {
        return userRepo.getUserByUsername(principal.getName()).orElseThrow(ApiNotFoundException::new);
    }
}
