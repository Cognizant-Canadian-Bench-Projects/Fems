package com.cognizant.feign.services;

import com.cognizant.feign.models.Location;

import java.util.List;

public interface LocationService {

    Location findLocationByName(String name);

    Location findLocationById(int id);

    List<Location> getAllLocations();
}
