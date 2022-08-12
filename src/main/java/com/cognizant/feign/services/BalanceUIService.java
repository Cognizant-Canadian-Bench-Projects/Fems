package com.cognizant.feign.services;

import com.cognizant.feign.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class BalanceUIService {

    @Autowired
    ProductService productService;

    @Autowired
    LocationService locationService;

    @Autowired
    BalanceService balanceService;

    public BalanceUI getProductByName(String productName) {
        Product product = productService.findProductByName(productName);
        List<Balance> balanceList = balanceService.findByProductId("" + product.getId());
        List<LocationQuantity> locationList = new ArrayList<>();
        int quantity = 0;
        for (Balance balance : balanceList
        ) {
            LocationQuantity locationQuantity = new LocationQuantity(locationService.findLocationById(balance.getLocationId()), balance.getQuantity());
            locationList.add(locationQuantity);
            quantity += balance.getQuantity();
        }
        return new BalanceUI(product.getId(), product, locationList, quantity);
    }

    public BalanceUI getProductByNameAndLocationName(String productName, String locationName) {
        Product product = productService.findProductByName(productName);
        Location location = locationService.findLocationByName(locationName);
        Balance balance = balanceService.findByProductIdAndLocationId("" + product.getId(), "" + location.getId());
        List<LocationQuantity> locationList = new ArrayList<>();
        int quantity = 0;
        LocationQuantity locationQuantity = new LocationQuantity(location, balance.getQuantity());
        locationList.add(locationQuantity);
        quantity = balance.getQuantity();
        return new BalanceUI(product.getId(), product, locationList, quantity);
    }

    public List<BalanceUI> getInventory() {
        List<Product> productList = productService.getAllProducts();
        List<BalanceUI> balanceUIS = new ArrayList<>();

        productList.parallelStream().forEach(item -> createBalanceUI(item, balanceUIS));

        return balanceUIS;
    }

    public List<BalanceUI> updateDistances(List<BalanceUI> balances,List<Location> updatedLocations)  {
       List<BalanceUI> updatedBalanceUI = new ArrayList<>();

        updatedLocations.stream().forEach(updatedLocation -> {
            balances.stream().filter(balance-> {
                AtomicBoolean updated = new AtomicBoolean(false);
                balance.getLocationList().stream().filter(locationQuantity ->
                        locationQuantity.getLocation().getZipcode().equals(updatedLocation.getZipcode()))
                    .forEach(locationQuantity -> {
                        updated.set(true);
                    locationQuantity.setLocation(updatedLocation);
                });
                return updated.get();
            }).forEach(balance -> {
               updatedBalanceUI.add(balance);
            });
        });


        return updatedBalanceUI;
    }

    private void createBalanceUI(Product product, List<BalanceUI> balanceUIS) {
        List<Balance> balanceList = balanceService.findByProductId("" + product.getId());
        List<LocationQuantity> locationList = new ArrayList<>();
        int quantity = 0;
        for (Balance balance : balanceList) {
            LocationQuantity locationQuantity = new LocationQuantity(locationService.findLocationById(balance.getLocationId()), balance.getQuantity());
            locationList.add(locationQuantity);
            quantity += balance.getQuantity();
        }
        balanceUIS.add(new BalanceUI(product.getId(), product, locationList, quantity));
    }
}
