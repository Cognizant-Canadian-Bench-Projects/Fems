package com.cognizant.feign.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Balance {

    private int id;
    private int productId;
    private int locationId;
    private int quantity;
}
