package com.cognizant.feign.controllers;

import com.cognizant.feign.exceptions.CustomFeignException;
import com.cognizant.feign.services.BalanceUIService;
import com.cognizant.feign.services.LocationServiceImpl;
import com.cognizant.feign.services.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FemsControllerTest {

  @Mock
  ProductServiceImpl productService;

  @Mock
  LocationServiceImpl locationService;

  @Mock
  BalanceUIService balanceUIService;

  @InjectMocks
  FemsController femsController;

  @BeforeEach
  void setUp() {
  }

  @Test
  void getProductByName() {
    femsController.findProductByName("shirt");
    Mockito.verify(productService, times(1)).findProductByName("shirt");
  }

  @Test
  void getProductByName_FeignException_404() {
    CustomFeignException e = new CustomFeignException(404, "You have an error");
    when(productService.findProductByName("shirts")).thenThrow(e);
    ResponseEntity<?> actual = femsController.findProductByName("shirts");
    assertThat(actual.getStatusCodeValue()).isEqualTo(404);
  }

  @Test
  void getProductByName_FeignException_400() {
    CustomFeignException e = new CustomFeignException(400, "You have an error");
    when(productService.findProductByName("shirts")).thenThrow(e);
    ResponseEntity<?> actual = femsController.findProductByName("shirts");
    assertThat(actual.getStatusCodeValue()).isEqualTo(400);
  }

  @Test
  void getProductByName_IllegalArgumentException() {
    when(productService.findProductByName("shirts")).thenThrow(IllegalArgumentException.class);
    ResponseEntity<?> actual = femsController.findProductByName("shirts");
    assertThat(actual.getStatusCodeValue()).isEqualTo(400);
  }

  @Test
  void getLocationById() {
    femsController.findLocationById(0);
    Mockito.verify(locationService, times(1)).findLocationById(0);
  }

  @Test
  void getLocationById_FeignException_404() {
    CustomFeignException e = new CustomFeignException(404, "You have an error");
    when(locationService.findLocationById(0)).thenThrow(e);
    ResponseEntity<?> actual = femsController.findLocationById(0);
    assertThat(actual.getStatusCodeValue()).isEqualTo(404);
  }

  @Test
  void getLocationById_FeignException_400() {
    CustomFeignException e = new CustomFeignException(400, "You have an error");
    when(locationService.findLocationById(0)).thenThrow(e);
    ResponseEntity<?> actual = femsController.findLocationById(0);
    assertThat(actual.getStatusCodeValue()).isEqualTo(400);
  }

  @Test
  void getLocationById_IllegalArgumentException() {
    when(locationService.findLocationById(0)).thenThrow(IllegalArgumentException.class);
    ResponseEntity<?> actual = femsController.findLocationById(0);
    assertThat(actual.getStatusCodeValue()).isEqualTo(400);
  }

  @Test
  void getLocationByName() {
    femsController.findLocationByName("toronto");
    Mockito.verify(locationService, times(1)).findLocationByName("toronto");
  }

  @Test
  void getLocationByName_FeignException_404() {
    CustomFeignException e = new CustomFeignException(404, "You have an error");
    when(locationService.findLocationByName("torrent")).thenThrow(e);
    ResponseEntity<?> actual = femsController.findLocationByName("torrent");
    assertThat(actual.getStatusCodeValue()).isEqualTo(404);
  }

  @Test
  void getLocationByName_FeignException_400() {
    CustomFeignException e = new CustomFeignException(400, "You have an error");
    when(locationService.findLocationByName("torontoo")).thenThrow(e);
    ResponseEntity<?> actual = femsController.findLocationByName("torontoo");
    assertThat(actual.getStatusCodeValue()).isEqualTo(400);
  }

  @Test
  void getLocationByName_IllegalArgumentException() {
    when(locationService.findLocationByName("torontoo")).thenThrow(IllegalArgumentException.class);
    ResponseEntity<?> actual = femsController.findLocationByName("torontoo");
    assertThat(actual.getStatusCodeValue()).isEqualTo(400);
  }

  @Test
  void getBalance_findByProductName() {
    femsController.getBalance("shirt", "");
    Mockito.verify(balanceUIService, times(1)).getProductByName("shirt");
  }

  @Test
  void getBalance_findByProductIdAndLocationId() {
    femsController.getBalance("shirt", "toronto");
    Mockito.verify(balanceUIService, times(1)).getProductByNameAndLocationName("shirt", "toronto");
  }

  @Test
  void getInventory() {
    femsController.getInventory();
    Mockito.verify(balanceUIService, times(1)).getInventory();
  }

  @Test
  void getBalance_FeignException_FindByProductNameAndLocationName_400() {
    CustomFeignException e = new CustomFeignException(400, "You have an error");
    when(balanceUIService.getProductByName("")).thenThrow(IllegalArgumentException.class);
    ResponseEntity<?> actual = femsController.getBalance("", "");
    assertThat(actual.getStatusCodeValue()).isEqualTo(400);
  }

//  @Test
//  void getBalance_FeignException_FindByProductIdAndLocation_400(){
//    CustomFeignException e = new CustomFeignException(400,"You have an error");
//    when(balanceService.findByProductIdAndLocationId("","2")).thenThrow(IllegalArgumentException.class);
//    ResponseEntity<?> actual = femsController.getBalance("");
//    assertThat(actual.getStatusCodeValue()).isEqualTo(400);
//  }

  @Test
  void getBalance_FeignException_FindByProductNameAndLocationName_404() {
    CustomFeignException e = new CustomFeignException(404, "Product Not Found");
    when(balanceUIService.getProductByName("")).thenThrow(e);
    ResponseEntity<?> actual = femsController.getBalance("", "");
    assertThat(actual.getStatusCodeValue()).isEqualTo(404);
  }

//  @Test
//  void getBalance_FeignException_FindByProductIdAndLocation_404(){
//    CustomFeignException e = new CustomFeignException(404,"Product Not Found");
//    when(balanceService.findByProductIdAndLocationId("","2")).thenThrow(e);
//    ResponseEntity<?> actual = femsController.getBalance("");
//    assertThat(actual.getStatusCodeValue()).isEqualTo(404);
//  }


}