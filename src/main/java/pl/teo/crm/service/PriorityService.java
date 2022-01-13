package pl.teo.crm.service;

import pl.teo.crm.model.Priority;
import pl.teo.crm.model.dto.PriorityCreationDto;

import java.util.Collection;
import java.util.List;

public interface PriorityService {
    void createPriority(PriorityCreationDto dto);

    List<String> getActivePrioritiesNames();
    List<Priority> getActivePriorities();
    Collection<Priority> getAll();
    Priority get(int id);

    Priority update(Priority priority);
}
