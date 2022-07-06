package com.cognizant.feign.client;

import com.cognizant.feign.models.Location;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="location", url = "${location.url}")
public interface LocationFeignClient {

    @GetMapping("/locations")
    Location findLocationByName(@RequestParam String name);
}
