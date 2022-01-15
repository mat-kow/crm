package pl.teo.crm.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.teo.crm.app.exception.ApiBadRequestException;
import pl.teo.crm.model.Status;
import pl.teo.crm.model.dto.StatusCreationDto;
import pl.teo.crm.service.StatusService;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api/statuses")
@RequiredArgsConstructor
@Slf4j
public class StatusController {

    private final StatusService statusService;

    @PostMapping("")
    public Status createNewStatus(@Valid @RequestBody StatusCreationDto dto) {
        return statusService.createStatus(dto);
    }

    @GetMapping("")
    public Collection<Status> getActiveStatuses() {
        return statusService.getActiveStatusList();
    }

    @GetMapping("/all")
    public Collection<Status> getAllStatuses() {
        return statusService.getAll();
    }

    @PutMapping("/{id}")
    public Status updateStatus(@PathVariable int id, @Valid @RequestBody Status status) {
        if (id == status.getId()) {
            return statusService.update(status);
        }
        throw new ApiBadRequestException("id from URL don't match entity id");
    }

    @GetMapping("/{id}")
    public Status getStatus(@PathVariable int id) {
        return statusService.get(id);
    }

}
