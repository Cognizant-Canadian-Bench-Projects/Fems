package com.cognizant.feign.services;

import com.cognizant.feign.client.LocationFeignClient;
import com.cognizant.feign.models.Location;
import org.junit.jupiter.api.Assertions;
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
  public void init() {
    location1 = new Location(1, "toronto", "m1p3r1t");
    location2 = new Location(1, "northyork", "m1p6r1t");
    location3 = new Location(1, "brampton", "m1p4r4t");
    location4 = new Location(1, "scarborough", "m1p6r1t");
  }

  @Test
  void getLocationByName() {
    when(locationFeignClient.findLocationByName("toronto")).thenReturn(location1);
    Location actual = locationService.findLocationByName("toronto");
    assertThat(actual).isEqualTo(location1);
  }

  @Test
  void getLocationByName_Negative() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      locationService.findLocationByName("");
    });
  }

  @Test
  void getLocationById_Positive() {
    when(locationFeignClient.findLocationById(1)).thenReturn(location1);
    Location actual = locationService.findLocationById(1);
    assertThat(actual).isEqualTo(location1);
  }

  @Test
  void getLocationById_Negative() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      locationService.findLocationById(0);
    });
  }
}
