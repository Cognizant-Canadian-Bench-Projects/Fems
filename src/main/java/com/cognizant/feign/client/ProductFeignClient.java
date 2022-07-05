package com.cognizant.feign.client;

import com.cognizant.feign.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "product", url = "${product.url}")
public interface ProductFeignClient {
//  @GetMapping("/products")
//  Product getAllProducts();

  @GetMapping("/products")
  Product findProductByName(@RequestParam String name);

  //Only be used by the balance service
//  @GetMapping("/products/{id}")
//  Product getProductById(@PathParam("id") int productId);
}
