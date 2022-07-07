package com.cognizant.feign.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BalanceUI {
    private Product product;
    private List<Location> locationList;
    private int quantity;

}
