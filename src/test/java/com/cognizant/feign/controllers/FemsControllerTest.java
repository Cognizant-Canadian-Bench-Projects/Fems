package com.cognizant.feign.controllers;

import com.cognizant.feign.exceptions.CustomFeignException;
import com.cognizant.feign.services.BalanceServiceImpl;
import com.cognizant.feign.services.LocationServiceImpl;
import com.cognizant.feign.services.ProductService;
import com.cognizant.feign.services.ProductServiceImpl;
import feign.FeignException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FemsControllerTest {

  @Mock
  ProductServiceImpl productService;

  @Mock
  LocationServiceImpl locationService;

  @Mock
  BalanceServiceImpl balanceService;

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
    CustomFeignException e = new CustomFeignException(404,"You have an error");
    when(productService.findProductByName("shirts")).thenThrow(e);
    ResponseEntity<?> actual = femsController.findProductByName("shirts");
    assertThat(actual.getStatusCodeValue()).isEqualTo(404);
  }

  @Test
  void getProductByName_FeignException_400() {
    CustomFeignException e = new CustomFeignException(400,"You have an error");
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
  void getLocationByName() {
    femsController.findLocationByName("toronto");
    Mockito.verify(locationService, times(1)).findLocationByName("toronto");
  }

  @Test
  void getLocationByName_FeignException_404() {
    CustomFeignException e = new CustomFeignException(404,"You have an error");
    when(locationService.findLocationByName("torrent")).thenThrow(e);
    ResponseEntity<?> actual = femsController.findLocationByName("torrent");
    assertThat(actual.getStatusCodeValue()).isEqualTo(404);
  }

  @Test
  void getLocationByName_FeignException_400() {
    CustomFeignException e = new CustomFeignException(400,"You have an error");
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
  void getBalance_findByProductId() {
    femsController.getBalance("2","");
    Mockito.verify(balanceService, times(1)).findByProductId("2");
  }

  @Test
  void getBalance_findByProductIdAndLocationId() {
    femsController.getBalance("2","2");
    Mockito.verify(balanceService, times(1)).findByProductIdAndLocationId("2","2");
  }

  @Test
  void getBalance_FeignException_FindByProductId_400(){
    CustomFeignException e = new CustomFeignException(400,"You have an error");
    when(balanceService.findByProductId("")).thenThrow(IllegalArgumentException.class);
    ResponseEntity<?> actual = femsController.getBalance("","");
    assertThat(actual.getStatusCodeValue()).isEqualTo(400);
  }

  @Test
  void getBalance_FeignException_FindByProductIdAndLocation_400(){
    CustomFeignException e = new CustomFeignException(400,"You have an error");
    when(balanceService.findByProductIdAndLocationId("","2")).thenThrow(IllegalArgumentException.class);
    ResponseEntity<?> actual = femsController.getBalance("","2");
    assertThat(actual.getStatusCodeValue()).isEqualTo(400);
  }

  @Test
  void getBalance_FeignException_FindByProductId_404(){
    CustomFeignException e = new CustomFeignException(404,"Product Not Found");
    when(balanceService.findByProductId("")).thenThrow(e);
    ResponseEntity<?> actual = femsController.getBalance("","");
    assertThat(actual.getStatusCodeValue()).isEqualTo(404);
  }

  @Test
  void getBalance_FeignException_FindByProductIdAndLocation_404(){
    CustomFeignException e = new CustomFeignException(404,"Product Not Found");
    when(balanceService.findByProductIdAndLocationId("","2")).thenThrow(e);
    ResponseEntity<?> actual = femsController.getBalance("","2");
    assertThat(actual.getStatusCodeValue()).isEqualTo(404);
  }
}