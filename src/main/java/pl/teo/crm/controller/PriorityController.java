package pl.teo.crm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.teo.crm.model.dto.PriorityDto;
import pl.teo.crm.service.PriorityService;

@RestController
@RequestMapping("/api/priority")
@RequiredArgsConstructor
public class PriorityController {

    private final PriorityService priorityService;

    @PostMapping("")
    public void createNewPriority(@RequestBody PriorityDto dto) {
        priorityService.createPriority(dto);
    }
}
