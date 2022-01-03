package pl.teo.crm.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.teo.crm.model.Status;
import pl.teo.crm.model.dto.StatusDto;
import pl.teo.crm.service.StatusService;

import java.util.Collection;

@RestController
@RequestMapping("/api/statuses")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class StatusController {

    private final StatusService statusService;

    @PostMapping("")
    public void createNewStatus(@RequestBody StatusDto dto) {
        statusService.createStatus(dto);
    }

    @PutMapping("/{statusId}/sorting/{sortValue}")
    public void setSortingValue(@PathVariable int statusId, @PathVariable int sortValue) {
        statusService.setSorting(statusId, sortValue);
    }

    @GetMapping("")
    public Collection<Status> getStatuses() {
        return statusService.getActiveStatusList();
    }

}
