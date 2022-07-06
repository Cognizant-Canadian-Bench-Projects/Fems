package com.cognizant.feign.services;

import com.cognizant.feign.models.Location;

public interface LocationService {

    Location findLocationByName(String name);
}
