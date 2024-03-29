package com.cognizant.feign.controllers;

import com.cognizant.feign.models.BalanceUI;
import com.cognizant.feign.models.Location;
import com.cognizant.feign.models.Product;
import com.cognizant.feign.services.BalanceUIService;
import com.cognizant.feign.services.LocationService;
import com.cognizant.feign.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(originPatterns = "*", exposedHeaders = "*", allowedHeaders = "*")
public class FemsController {
  @Autowired
  ProductService productService;

  @Autowired
  LocationService locationService;

  @Autowired
  BalanceUIService balanceUIService;

  @GetMapping("/products")
  public ResponseEntity<?> findProductByName(@RequestParam String name) {
    Product product = null;
    try {
      product = productService.findProductByName(name);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(400).body(e.getMessage());
    }
    return ResponseEntity.ok(product);
  }


  @GetMapping("/locations")
  public ResponseEntity<?> findLocationByName(@RequestParam String name) {
    Location location = null;
    try {
      location = locationService.findLocationByName(name);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(400).body(e.getMessage());
    }
    return ResponseEntity.ok(location);
  }

  @GetMapping("/locations/{id}")
  public ResponseEntity<?> findLocationById(@PathVariable("id") int id) {
    Location location = null;
    try {
      location = locationService.findLocationById(id);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(400).body(e.getMessage());
    }
    return ResponseEntity.ok(location);
  }

  @GetMapping("/balance")
  public ResponseEntity<?> getBalance(@RequestParam String productName, @RequestParam(required = false) String locationName) {
    try {
      BalanceUI balanceUI;
      if (locationName == null || locationName.equals("")) {
        balanceUI = balanceUIService.getProductByName(productName);
      } else {
        balanceUI = balanceUIService.getProductByNameAndLocationName(productName, locationName);
      }
      return ResponseEntity.ok(balanceUI);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(400).body(e.getMessage());
    }
  }

  @GetMapping("/inventory")
  public ResponseEntity<?> getInventory() throws InterruptedException {
    return ResponseEntity.ok(balanceUIService.getInventory());
  }
}


