package com.cognizant.feign.services;

import com.cognizant.feign.client.ProductFeignClient;
import com.cognizant.feign.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductFeignClient productFeignClient;

    @Override
    public Product findProductByName(String name) {
        if (name.equals("")) {
            throw new IllegalArgumentException("Please provide the product name");
        }
        return productFeignClient.findProductByName(name);
    }

    @Override
    public List<Product> getAllProducts() {
        return productFeignClient.getAllProducts();
    }

}


