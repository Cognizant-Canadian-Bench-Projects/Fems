package com.cognizant.feign.controllers;

import com.cognizant.feign.models.Balance;
import com.cognizant.feign.models.BalanceUI;
import com.cognizant.feign.models.Location;
import com.cognizant.feign.models.Product;
import com.cognizant.feign.services.BalanceService;
import com.cognizant.feign.services.BalanceUIService;
import com.cognizant.feign.services.LocationService;
import com.cognizant.feign.services.ProductService;
import com.cognizant.feign.util.FeignUtil;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;


@RestController
@CrossOrigin(originPatterns = "*", exposedHeaders = "*",allowedHeaders = "*")
public class FemsController {
    @Autowired
    ProductService productService;

    @Autowired
    LocationService locationService;

    @Autowired
    BalanceService balanceService;

    @Autowired
    BalanceUIService balanceUIService;

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

    @GetMapping("/locations/{id}")
    public ResponseEntity<?> findLocationById(@PathVariable("id") int id) {
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
    public ResponseEntity<?> getBalance(@RequestParam String productName, @RequestParam(required = false) String locationName) {
        try {
            if(locationName==null || locationName.equals("")) {
                BalanceUI balanceUI = balanceUIService.getProductByName(productName);
                return ResponseEntity.ok(balanceUI);
            }
            else{
                 BalanceUI balanceUI = balanceUIService.getProductByNameAndLocationName(productName,locationName);
                 return ResponseEntity.ok(balanceUI);
            }
        } catch (FeignException e) {
            return FeignUtil.checkFeignException(e);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    //TODO 1: Get all balances
    @GetMapping("inventory")
    public ResponseEntity<?> getInventory(){
        return ResponseEntity.ok(balanceUIService.getInventory());
    }
}


