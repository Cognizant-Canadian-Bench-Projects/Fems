package com.cognizant.feign.controllers;

import com.cognizant.feign.models.Location;
import com.cognizant.feign.models.Product;
import com.cognizant.feign.services.LocationService;
import com.cognizant.feign.services.ProductService;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@CrossOrigin
public class FemsController {
  @Autowired
  ProductService productService;

  @Autowired
  LocationService locationService;

  @GetMapping("/products")
  public ResponseEntity<?> findProductByName(@RequestParam String name) {
    Product product = null;
    try {
      product = productService.findProductByName(name);
    }catch(FeignException e) {
      return checkFeignException(e,name);
    }catch(IllegalArgumentException e){
      return ResponseEntity.status(400).body(e.getMessage());
    }
    return ResponseEntity.ok(product);
  }

  private ResponseEntity<?> checkFeignException(FeignException e, String name){
    switch(e.status()){
      case 404:
        return ResponseEntity.status(404).body(name + " does not exist");
      default:
        return ResponseEntity.status(400).body("Bad Request");
    }
  }

  @GetMapping("/locations")
  public ResponseEntity<?> findLocationByName(@RequestParam String name) {
    Location location = null;
    try {
      location = locationService.findLocationByName(name);
    }catch(FeignException e) {
      return checkFeignException(e,name);
    }catch(IllegalArgumentException e){
      return ResponseEntity.status(400).body(e.getMessage());
    }
    return ResponseEntity.ok(location);
  }
}


