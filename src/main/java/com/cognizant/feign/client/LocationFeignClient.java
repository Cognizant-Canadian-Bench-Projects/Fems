package com.cognizant.feign.client;

import com.cognizant.feign.models.Location;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "location", url = "${location.url}")
public interface LocationFeignClient {

    @GetMapping("/locations")
    Location findLocationByName(@RequestParam String name);

    @GetMapping("/locations")
    List<Location> getAllLocations();

    @GetMapping("/locations/{id}")
    Location findLocationById(@PathVariable("id") int id);

    @GetMapping("/geoname")
    List<Location> getNearLocationByZipcode(@RequestParam String zipcode, @RequestParam String country, @RequestParam int radius);


}
