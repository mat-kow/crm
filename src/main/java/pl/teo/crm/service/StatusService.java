package pl.teo.crm.service;

import pl.teo.crm.model.Status;
import pl.teo.crm.model.dto.StatusDto;

import java.util.Collection;

public interface StatusService {
    void createStatus(StatusDto dto);

    Collection<Status> getActiveStatusList();
    boolean activateStatus(int statusId);
    boolean deactivateStatus(int statusId);

    void setSorting(int statusId, int sortValue);

}
