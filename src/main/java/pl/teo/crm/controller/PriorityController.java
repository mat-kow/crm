package pl.teo.crm.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.teo.crm.model.dto.PriorityDto;
import pl.teo.crm.service.PriorityService;

import java.util.List;

@RestController
@RequestMapping("/api/priorities")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class PriorityController {

    private final PriorityService priorityService;

    @PostMapping("")
    public void createNewPriority(@RequestBody PriorityDto dto) {
        priorityService.createPriority(dto);
    }

    @GetMapping("")
    public List<String> getPriorities() {
        log.info("Fetching active priorities");
        return priorityService.getActivePriorities();
    }
}
