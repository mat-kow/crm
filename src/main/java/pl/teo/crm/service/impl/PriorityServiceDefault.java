package pl.teo.crm.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.teo.crm.model.Priority;
import pl.teo.crm.model.dto.PriorityDto;
import pl.teo.crm.model.repository.PriorityRepo;
import pl.teo.crm.service.PriorityService;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PriorityServiceDefault implements PriorityService {

    private final PriorityRepo priorityRepo;
    private final ModelMapper mapper;

    @Override
    @Transactional
    public void createPriority(PriorityDto dto) {
        Priority priority = mapper.map(dto, Priority.class);
        priority.setName(priority.getName().toLowerCase(Locale.ROOT));
        priority.setActive(true);
        priorityRepo.save(priority);
    }

    @Override
    @Transactional
    public boolean activatePriority(String priorityName) {
        Priority priority = priorityRepo.findById(priorityName).orElseThrow(RuntimeException::new); //todo custom exception
        if (priority.isActive()) {
            return false;
        }
        priority.setActive(true);
        priorityRepo.save(priority);
        return true;
    }
    @Override
    @Transactional
    public boolean deactivatePriority(String priorityName) {
        Priority priority = priorityRepo.findById(priorityName).orElseThrow(RuntimeException::new); //todo custom exception
        if (!priority.isActive()) {
            return false;
        }
        priority.setActive(false);
        priorityRepo.save(priority);
        return true;
    }

    @Override
    public List<Priority> getActivePriorities() {
        return priorityRepo.getAllByActiveTrue();
    }

    @Override
    public List<String> getActivePrioritiesNames() {
        return priorityRepo.getAllByActiveTrue().stream()
                .map(Priority::getName).collect(Collectors.toList());
    }


}
