package pl.teo.crm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.teo.crm.model.dto.StatusDto;
import pl.teo.crm.service.StatusService;

@RestController
@RequestMapping("/api/status")
@RequiredArgsConstructor
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
}
