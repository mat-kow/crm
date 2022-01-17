package pl.teo.crm.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.teo.crm.model.Activity;
import pl.teo.crm.service.ActivityService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/activities")
@RequiredArgsConstructor
@Slf4j
public class ActivityController {

    private final ActivityService activityService;

    @GetMapping("/all")
    public Collection<Activity> getAllActivities() {
        return activityService.getAll();
    }

    @GetMapping("")
    public List<Activity> getActive25Activities() {
        log.info("fetching latest activities");
        return activityService.getLatestActive(25);
    }
}
