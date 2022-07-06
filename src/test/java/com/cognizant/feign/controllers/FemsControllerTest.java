package com.cognizant.feign.controllers;

import com.cognizant.feign.exceptions.CustomFeignException;
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
}