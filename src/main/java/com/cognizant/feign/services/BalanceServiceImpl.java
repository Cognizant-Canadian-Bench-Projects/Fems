package com.cognizant.feign.services;

import com.cognizant.feign.client.BalanceFeignClient;
import com.cognizant.feign.models.Balance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BalanceServiceImpl implements BalanceService {

    @Autowired
    private BalanceFeignClient balanceFeignClient;


    @Override
    public List<Balance> findByProductId(String productId) {
        List<Balance> balanceList = new ArrayList<>();
        if (productId.equals("")) {
            throw new IllegalArgumentException("Please provide a productId");
        }
        return balanceFeignClient.findByProductId(productId);
    }

    @Override
    public Balance findByProductIdAndLocationId(String productId, String locationId) {
        try {
            int intProductId = Integer.parseInt(productId);
            int intLocationId = Integer.parseInt(locationId);
            Balance balance = balanceFeignClient.findByProductIdAndLocationId(intProductId, intLocationId);
            return balance;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Product id or location id is illegal");
        }
    }
}
