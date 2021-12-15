package pl.teo.crm.service;

import pl.teo.crm.model.dto.PriorityDto;

import java.util.List;

public interface PriorityService {
    void createPriority(PriorityDto dto);

    boolean activatePriority(String priorityName);
    boolean deactivatePriority(String priorityName);

    List<String> getActivePriorities();
}
