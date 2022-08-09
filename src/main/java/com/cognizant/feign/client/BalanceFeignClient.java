package com.cognizant.feign.client;

import com.cognizant.feign.models.Balance;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "balance", url = "${balance.url}")
public interface BalanceFeignClient {

    @GetMapping("/balance")
    List<Balance> findByProductId(@RequestParam String productId);

    @GetMapping("/balance")
    Balance findByProductIdAndLocationId(@RequestParam int productId, @RequestParam int locationId);

    @GetMapping("/balance")
    List<Balance> getAllBalance();
}
