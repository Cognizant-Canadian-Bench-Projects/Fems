package com.cognizant.feign.controllers;

import com.cognizant.feign.services.ProductService;
import com.cognizant.feign.services.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

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
   femsController.getProductByName("shirt");
   Mockito.verify(productService, times(1)).getProductByName("shirt");
  }
}