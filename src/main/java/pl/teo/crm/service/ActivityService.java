package pl.teo.crm.service;

import pl.teo.crm.model.Activity;

import java.util.Collection;
import java.util.List;

public interface ActivityService {
    Collection<Activity> getAll();
    List<Activity> getLatestActive(int limit);

}
