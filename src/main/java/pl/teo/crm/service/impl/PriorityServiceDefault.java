package pl.teo.crm.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.teo.crm.model.Priority;
import pl.teo.crm.model.dto.PriorityDto;
import pl.teo.crm.model.repository.PriorityRepo;
import pl.teo.crm.service.PriorityService;

import java.util.Collection;
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
    public List<Priority> getActivePriorities() {
        return priorityRepo.getAllByActiveTrue();
    }

    @Override
    public Collection<Priority> getAll() {
        return priorityRepo.findAll();
    }

    @Override
    public Priority get(int id) {
        return priorityRepo.findById(id).orElseThrow(RuntimeException::new); //todo exception
    }

    @Override
    @Transactional
    public Priority update(Priority priority) {
        return priorityRepo.save(priority);
    }

    @Override
    public List<String> getActivePrioritiesNames() {
        return priorityRepo.getAllByActiveTrue().stream()
                .map(Priority::getName).collect(Collectors.toList());
    }

}
