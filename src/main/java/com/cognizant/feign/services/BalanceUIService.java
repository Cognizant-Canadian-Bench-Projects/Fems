package com.cognizant.feign.services;

import com.cognizant.feign.models.Balance;
import com.cognizant.feign.models.BalanceUI;
import com.cognizant.feign.models.Location;
import com.cognizant.feign.models.Product;
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
        List<Location> locationList = new ArrayList<>();
        int quantity = 0;
        for (Balance balance : balanceList
        ) {
            locationList.add(locationService.findLocationById(balance.getLocationId()));
            quantity += balance.getQuantity();
        }

        BalanceUI balanceUI = new BalanceUI(product, locationList, quantity);
        return balanceUI;
    }
}
