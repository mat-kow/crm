package pl.teo.crm.service;

import pl.teo.crm.model.Status;
import pl.teo.crm.model.dto.StatusCreationDto;

import java.util.Collection;

public interface StatusService {
    Status createStatus(StatusCreationDto dto);

    Collection<Status> getActiveStatusList();
    Collection<Status> getAll();
    Status update(Status status);
    Status get(int id);
}
