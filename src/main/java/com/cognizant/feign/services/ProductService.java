package com.cognizant.feign.services;

import com.cognizant.feign.models.Product;

public interface ProductService {
  //Product getAllProducts();
  Product findProductByName(String name);
  //Only be used by the balance service
  //Product getProductById(String productId);
}
