package com.cognizant.feign.services;

import com.cognizant.feign.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BalanceUIService {

    @Autowired
    ProductService productService;

    @Autowired
    LocationService locationService;

    @Autowired
    BalanceService balanceService;

    public BalanceUI getProductByName(String productName) {
        Product product = productService.findProductByName(productName);
        List<Balance> balanceList = balanceService.findByProductId("" + product.getId());
        List<LocationQuantity> locationList = new ArrayList<>();
        int quantity = 0;
        for (Balance balance : balanceList
        ) {
            LocationQuantity locationQuantity = new LocationQuantity(locationService.findLocationById(balance.getLocationId()),balance.getQuantity());
            locationList.add(locationQuantity);
            quantity += balance.getQuantity();
        }

        BalanceUI balanceUI = new BalanceUI(product, locationList, quantity);
        return balanceUI;
    }
}
