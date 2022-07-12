package com.cognizant.feign.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LocationQuantity {
    private Location location;
    private int quantity;
}
