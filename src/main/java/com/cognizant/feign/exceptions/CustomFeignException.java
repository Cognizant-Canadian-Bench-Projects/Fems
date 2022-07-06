package com.cognizant.feign.exceptions;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND)

public class CustomFeignException extends FeignException {
  public CustomFeignException(int status, String message) {
    super(status, message);
  }
}
