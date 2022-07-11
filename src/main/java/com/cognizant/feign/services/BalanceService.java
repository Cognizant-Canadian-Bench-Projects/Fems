package com.cognizant.feign.services;

import com.cognizant.feign.models.Balance;
import com.cognizant.feign.models.Location;

import java.util.List;

public interface BalanceService {

    List<Balance> findByProductId(String productId);

    Balance findByProductIdAndLocationId(String productId, String locationId);
}
