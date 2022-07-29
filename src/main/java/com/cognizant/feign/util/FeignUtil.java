package com.cognizant.feign.util;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class FeignUtil implements ErrorDecoder {
  @Override
  public Exception decode(String methodKey, Response response) {
    switch (response.status()) {
      case 400:
        return new ResponseStatusException(HttpStatus.valueOf(response.status()), "Product id or location id is illegal")
      break;
      case 404:
        if (methodKey.contains("findByProductId")) {
          return new ResponseStatusException(HttpStatus.valueOf(response.status()), "Product with id " + response.request().url().substring(response.request().url().indexOf("=") + 1) + " is not in inventory");
        } else if (methodKey.contains("findProductByName")) {
          return new ResponseStatusException(HttpStatus.valueOf(response.status()), "Product with name " + response.request().url().substring(response.request().url().indexOf("=") + 1) + " is invalid");
        } else if (methodKey.contains("findLocationById")) {
          return new ResponseStatusException(HttpStatus.valueOf(response.status()), "Location with id " + response.request().url().substring(response.request().url().lastIndexOf('/') + 1) + " could not be located");
        }
        break;
      default:
        return new ResponseStatusException(HttpStatus.valueOf(response.status()), "Something went wrong");
    }
    return new ResponseStatusException(HttpStatus.valueOf(response.status()), response.request().url());
  }
}
