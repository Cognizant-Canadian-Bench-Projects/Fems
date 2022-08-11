package com.cognizant.feign.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Location {
    private int id;
    private String name;
    private String zipcode;
    private double distance;

}
