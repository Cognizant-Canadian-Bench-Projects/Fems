package com.cognizant.feign.controllers;

import com.cognizant.feign.models.Product;
import com.cognizant.feign.services.ProductService;
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

  @GetMapping("/products")
  public ResponseEntity<?> findProductByName(@RequestParam String name) {
    try {
    Product product = productService.findProductByName(name);
    return ResponseEntity.ok(product);
    }catch(IllegalArgumentException e) {
      return ResponseEntity.status(400).body(e.getMessage());
    }
  }
}
