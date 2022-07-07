package com.cognizant.feign.services;

import com.cognizant.feign.models.Balance;
import com.cognizant.feign.models.BalanceUI;
import com.cognizant.feign.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BalanceUIService {

    @Autowired
    ProductService productService;

    @Autowired
    LocationService locationService;

    @Autowired
    BalanceService balanceService;

    public BalanceUI getProductByName(String productName){
    Product product = productService.findProductByName(productName);
    return null;
    }
}
