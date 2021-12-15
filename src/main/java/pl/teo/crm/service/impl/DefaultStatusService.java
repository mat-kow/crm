package pl.teo.crm.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.teo.crm.model.Status;
import pl.teo.crm.model.dto.StatusDto;
import pl.teo.crm.model.repository.StatusRepo;
import pl.teo.crm.service.StatusService;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class DefaultStatusService implements StatusService {

    private final StatusRepo statusRepo;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public void createStatus(StatusDto dto) {
        Status status = modelMapper.map(dto, Status.class);
        status.setActive(true);
        status.setSortingValue(0);
        statusRepo.save(status);
    }

    @Override
    public Collection<Status> getActiveStatusList() {
        return statusRepo.getAllByActiveTrue();
    }

    @Override
    @Transactional
    public boolean activateStatus(int statusId) {
        Status status = statusRepo.findById(statusId).orElseThrow(RuntimeException::new); //todo custom exception
        if (status.isActive()) {
            return false;
        }
        status.setActive(true);
        statusRepo.save(status);
        return true;
    }
    @Override
    @Transactional
    public boolean deactivateStatus(int statusId) {
        Status status = statusRepo.findById(statusId).orElseThrow(RuntimeException::new); //todo custom exception
        if (!status.isActive()) {
            return false;
        }
        status.setActive(false);
        statusRepo.save(status);
        return true;
    }

    @Override
    @Transactional
    public void setSorting(int statusId, int sortValue) {
        Status status = statusRepo.findById(statusId).orElseThrow(RuntimeException::new); //todo custom exception;
        status.setSortingValue(sortValue);
        statusRepo.save(status);
    }

}
