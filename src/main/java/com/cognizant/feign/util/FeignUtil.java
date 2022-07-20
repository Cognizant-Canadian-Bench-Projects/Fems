package com.cognizant.feign.util;

import feign.FeignException;
import org.springframework.http.ResponseEntity;

public class FeignUtil {
    public static ResponseEntity<?> checkFeignException(FeignException e) {
        switch (e.status()) {
            case 404:
                return ResponseEntity.status(404).body("product or location does not exist with this name or id");
            default:
                return ResponseEntity.status(400).body("Bad Request");
        }
    }


}
