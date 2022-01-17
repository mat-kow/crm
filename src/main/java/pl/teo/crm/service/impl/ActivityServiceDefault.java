package pl.teo.crm.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.teo.crm.model.Activity;
import pl.teo.crm.model.repository.ActivityRepo;
import pl.teo.crm.service.ActivityService;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityServiceDefault implements ActivityService {

    private final ActivityRepo activityRepo;

    @Override
    public Collection<Activity> getAll() {
        return activityRepo.findAll();
    }

    @Override
    public List<Activity> getLatestActive(int limit) {
        return activityRepo.findAllOrderedDesc(limit);
    }
}
