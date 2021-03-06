package com.cognizant.feign.services;

import com.cognizant.feign.client.ProductFeignClient;
import com.cognizant.feign.models.Department;
import com.cognizant.feign.models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

  @Mock
  ProductFeignClient productFeignClient;

  @InjectMocks
  ProductServiceImpl productService;

  Product product1;
  Product product2;
  Product product3;
  Product product4;

  Department department1;
  Department department2;


  @BeforeEach
  void setUp() {

    department1 = new Department(1, "clothing");
    product1 = new Product(1, "shirt", department1);
    product2 = new Product(1, "pant", department1);
    product3 = new Product(1, "glove", department1);
    product4 = new Product(1, "cap", department1);
  }

  @Test
  void getProductByName() {
    when(productFeignClient.findProductByName("shirt")).thenReturn(product1);
    Product actual = productService.findProductByName("shirt");
    assertThat(actual).isEqualTo(product1);
  }

  @Test
  void getProductByName_Negative() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      productService.findProductByName("");
    });
  }

  @Test
  void getAllProducts() {
    productService.getAllProducts();
    Mockito.verify(productFeignClient, times(1)).getAllProducts();
  }
}