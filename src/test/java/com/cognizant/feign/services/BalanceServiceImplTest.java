package com.cognizant.feign.services;

import com.cognizant.feign.client.BalanceFeignClient;
import com.cognizant.feign.models.Balance;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BalanceServiceImplTest {

  @Mock
  BalanceFeignClient balanceFeignClient;

  @InjectMocks
  BalanceServiceImpl balanceService;

  Balance balance1;
  Balance balance2;
  Balance balance3;
  Balance balance4;

  @BeforeEach
  void init() {
    balance1 = new Balance(1, 2, 3, 123);
    balance2 = new Balance(2, 3, 4, 234);
    balance3 = new Balance(3, 4, 5, 345);
    balance4 = new Balance(4, 2, 6, 456);
  }

  @Test
  void findByProductId_Positive() {
    List<Balance> balanceList = new ArrayList<>();
    balanceList.add(balance1);
    balanceList.add(balance4);
    when(balanceFeignClient.findByProductId("2")).thenReturn(balanceList);
    List<Balance> actual = balanceService.findByProductId("2");
    assertThat(actual).isEqualTo(balanceList);
  }

  @Test
  void findByProductIdAndLocationId_Positive() {
    when(balanceFeignClient.findByProductIdAndLocationId(2, 3)).thenReturn(balance1);
    Balance actual = balanceService.findByProductIdAndLocationId("2", "3");
    assertThat(actual).isEqualTo(balance1);
  }

  @Test
  void findByProductIdAndLocationIdNumberFormatException_Negative() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      Balance actual = balanceService.findByProductIdAndLocationId("a", "b");
    });
  }

  @Test
  void findByProductId_Negative() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      List<Balance> actual = balanceService.findByProductId("");
    });
  }

  @Test
  void getAllBalance() {
    balanceService.getAllBalance();
    Mockito.verify(balanceFeignClient, times(1)).getAllBalance();
  }
}