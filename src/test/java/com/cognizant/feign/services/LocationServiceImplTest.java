package com.cognizant.feign.services;

import com.cognizant.feign.client.LocationFeignClient;
import com.cognizant.feign.models.Location;
import com.cognizant.feign.models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationServiceImplTest {

    @Mock
    LocationFeignClient locationFeignClient;

    @InjectMocks
    LocationServiceImpl locationService;

    Location location1;
    Location location2;
    Location location3;
    Location location4;

    @BeforeEach
    public  void init(){
        location1 =new Location(1,"toronto","m1p3r1t");
        location2 =new Location(1,"northyork","m1p6r1t");
        location3 =new Location(1,"brampton","m1p4r4t");
        location4 =new Location(1,"scarborough","m1p6r1t");
    }

    @Test
    void getLocationByName() {
        when(locationFeignClient.findLocationByName("toronto")).thenReturn(location1);
       Location actual = locationService.findLocationByName("toronto");
        assertThat(actual).isEqualTo(location1);
    }
}
