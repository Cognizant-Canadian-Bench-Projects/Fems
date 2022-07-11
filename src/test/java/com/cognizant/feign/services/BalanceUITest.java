package com.cognizant.feign.services;

import com.cognizant.feign.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BalanceUITest {

    @Mock
    LocationService locationService;

    @Mock
    BalanceService balanceService;

    @Mock
    ProductService productService;

    @InjectMocks
    BalanceUIService balanceUIService;

    Department department1;
    Product product1;
    Location location1;
    Balance balance1;
    Balance balance2;
    BalanceUI balanceUI1;
    BalanceUI balanceUI2;
    List<Location> locationList;
    List<Balance> balanceList;
    @BeforeEach
    void setUp(){
        department1=new Department(1,"clothing");
        product1 = new Product(1,"shirt",department1);
        location1 =new Location(1,"toronto","m1p3r1t");
        locationList= new ArrayList<>();
        locationList.add(location1);
        locationList.add(location1);
        balance1 = new Balance(1,2,3,100);
        balance2 = new Balance(2,3,4,200);
        balanceList = new ArrayList<>();
        balanceList.add(balance1);
        balanceList.add(balance2);
        balanceUI1 = new BalanceUI(product1,locationList,300);

    }

    @Test
    void getProductByName(){
        when(productService.findProductByName("shirt")).thenReturn(product1);
        when(balanceService.findByProductId(""+product1.getId())).thenReturn(balanceList);
        when(locationService.findLocationById(balance1.getLocationId())).thenReturn(location1);
        when(locationService.findLocationById(balance2.getLocationId())).thenReturn(location1);
        BalanceUI actual = balanceUIService.getProductByName("shirt");
        assertThat(actual).isEqualTo(balanceUI1);
    }

}
