package pl.teo.crm.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.teo.crm.app.exception.ApiBadRequestException;
import pl.teo.crm.model.Priority;
import pl.teo.crm.model.dto.PriorityDto;
import pl.teo.crm.service.PriorityService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/priorities")
@RequiredArgsConstructor
@Slf4j
public class PriorityController {

    private final PriorityService priorityService;

    @PostMapping("")
    public void createNewPriority(@RequestBody PriorityDto dto) {
        priorityService.createPriority(dto);
    }

    @GetMapping("/names")
    public List<String> getPrioritiesNames() {
        log.info("Fetching active priorities names");
        return priorityService.getActivePrioritiesNames();
    }

    @GetMapping("")
    public List<Priority> getActivePriorities() {
        log.info("Fetching active priorities");
        return priorityService.getActivePriorities();
    }

    @GetMapping("/all")
    public Collection<Priority> getAllPriorities() {
        log.info("Fetching all priorities");
        return priorityService.getAll();
    }

    @GetMapping("/{id}")
    public Priority getPriority(@PathVariable int id) {
        log.info(String.format("Fetching priority id <%d>", id));
        return priorityService.get(id);
    }

    @PutMapping("/{id}")
    public Priority updatePriority(@PathVariable int id, @RequestBody Priority priority) {
        log.info(String.format("updating priority <%s>", priority.getName()));
        if (id == priority.getId()) {
            return priorityService.update(priority);
        }
        throw new ApiBadRequestException("id from URL don't match entity id");
    }
}
