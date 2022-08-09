package com.cognizant.feign.services;

import com.cognizant.feign.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    Product findProductByName(String name);
}
