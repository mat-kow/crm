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
public class StatusServiceDefault implements StatusService {

    private final StatusRepo statusRepo;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public Status createStatus(StatusDto dto) {
        Status status = modelMapper.map(dto, Status.class);
        status.setActive(true);
        return statusRepo.save(status);
    }

    @Override
    public Collection<Status> getActiveStatusList() {
        return statusRepo.getAllByActiveTrue();
    }

    @Override
    public Collection<Status> getAll() {
        return statusRepo.findAll();
    }

    @Override
    @Transactional
    public Status update(Status status) {
        return statusRepo.save(status);
    }

    @Override
    public Status get(int id) {
        return statusRepo.findById(id).orElseThrow(RuntimeException::new); //todo exception
    }

}
