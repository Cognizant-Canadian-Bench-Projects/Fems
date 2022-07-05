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
@CrossOrigin(origins = "${ui.url}", allowCredentials = "true")
public class FemsController {
  @Autowired
  ProductService productService;

  @GetMapping("/products")
  public ResponseEntity<?> getProductByName(@RequestParam String name) {
    Product product = productService.getProductByName(name);
    return ResponseEntity.ok(product);
  }
}
