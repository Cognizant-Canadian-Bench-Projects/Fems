package com.cognizant.feign.controllers;

import com.cognizant.feign.models.Balance;
import com.cognizant.feign.models.Location;
import com.cognizant.feign.models.Product;
import com.cognizant.feign.services.BalanceService;
import com.cognizant.feign.services.LocationService;
import com.cognizant.feign.services.ProductService;
import com.cognizant.feign.util.FeignUtil;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
public class FemsController {
    @Autowired
    ProductService productService;

    @Autowired
    LocationService locationService;

    @Autowired
    BalanceService balanceService;

    @GetMapping("/products")
    public ResponseEntity<?> findProductByName(@RequestParam String name) {
        Product product = null;
        try {
            product = productService.findProductByName(name);
        } catch (FeignException e) {
            return FeignUtil.checkFeignException(e);
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
        } catch (FeignException e) {
            return FeignUtil.checkFeignException(e);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
        return ResponseEntity.ok(location);
    }

    @GetMapping("/locations/${id}")
    public ResponseEntity<?> findLocationById(@PathVariable int id) {
        Location location = null;
        try {
            location = locationService.findLocationById(id);
        } catch (FeignException e) {
            return FeignUtil.checkFeignException(e);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
        return ResponseEntity.ok(location);
    }

    @GetMapping("/balance")
    public ResponseEntity<?> getBalance(@RequestParam String productId, @RequestParam String locationId) {
        List<Balance> balanceList = null;
        Balance balance = null;
        try {
            if (locationId.equals("")) {
                balanceList = balanceService.findByProductId(productId);
                return ResponseEntity.ok(balanceList);
            } else {
                balance = balanceService.findByProductIdAndLocationId(productId, locationId);
                return ResponseEntity.ok(balance);
            }
        } catch (FeignException e) {
            return FeignUtil.checkFeignException(e);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}


