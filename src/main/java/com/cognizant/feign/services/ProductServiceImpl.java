package com.cognizant.feign.services;

import com.cognizant.feign.client.ProductFeignClient;
import com.cognizant.feign.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{
  @Autowired
  private ProductFeignClient productFeignClient;

  @Override
  public Product getProductByName(String name) {
    return productFeignClient.getProductByName(name);
  }
}
