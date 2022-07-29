package com.cognizant.feign.client;

import com.cognizant.feign.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "product", url = "${product.url}")
public interface ProductFeignClient {
  @GetMapping("/products")
  List<Product> getAllProducts();

  @GetMapping("/products")
  Product findProductByName(@RequestParam String name);
}
