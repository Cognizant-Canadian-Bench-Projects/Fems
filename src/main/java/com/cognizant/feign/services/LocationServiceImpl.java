package com.cognizant.feign.services;

import com.cognizant.feign.client.LocationFeignClient;
import com.cognizant.feign.models.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements LocationService {

  @Autowired
  private LocationFeignClient locationFeignClient;

  @Override
  public Location findLocationByName(String name) {
    if (name.equals("")) {
      throw new IllegalArgumentException("Please provide the location name");
    }
    return locationFeignClient.findLocationByName(name);
  }

  @Override
  public Location findLocationById(int id) {
    if (id <= 0) {
      throw new IllegalArgumentException("Please provide the location id");
    }
    return locationFeignClient.findLocationById(id);
  }
}
