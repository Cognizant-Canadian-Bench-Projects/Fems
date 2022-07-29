package com.cognizant.feign.util;

import feign.Request;
import feign.Response;
import feign.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class FeignUtilTest {

  FeignUtil feignUtil;
  Response response;
  private final Map<String, Collection<String>> headers = new LinkedHashMap<>();

  @BeforeEach
  void setup() {
    feignUtil = new FeignUtil();
  }

  @Test
  void decode_400() {
    response = Response.builder()
        .status(400)
        .reason("Internal server error")
        .request(Request.create(Request.HttpMethod.GET, "", Collections.emptyMap(), null, Util.UTF_8))
        .headers(headers)
        .build();

    ResponseStatusException e = this.feignUtil.decode("", response);
    assertThat(e.getReason()).isEqualTo("Product id or location id is illegal");
  }

  @Test
  void decode_404_findByProductId() {
    response = Response.builder()
        .status(404)
        .reason("Internal server error")
        .request(Request.create(Request.HttpMethod.GET, "/products?id=1", Collections.emptyMap(), null, Util.UTF_8))
        .headers(headers)
        .build();

    ResponseStatusException e = this.feignUtil.decode("findByProductId", response);
    assertThat(e.getReason()).isEqualTo("Product with id 1 is not in inventory");
  }

  @Test
  void decode_404_findProductByName() {
    response = Response.builder()
        .status(404)
        .reason("Internal server error")
        .request(Request.create(Request.HttpMethod.GET, "/products?name=blah", Collections.emptyMap(), null, Util.UTF_8))
        .headers(headers)
        .build();

    ResponseStatusException e = this.feignUtil.decode("findProductByName", response);
    assertThat(e.getReason()).isEqualTo("Product with name blah is invalid");
  }

  @Test
  void decode_404_findLocationById() {
    response = Response.builder()
        .status(404)
        .reason("Internal server error")
        .request(Request.create(Request.HttpMethod.GET, "/locations/1", Collections.emptyMap(), null, Util.UTF_8))
        .headers(headers)
        .build();

    ResponseStatusException e = this.feignUtil.decode("findLocationById", response);
    assertThat(e.getReason()).isEqualTo("Location with id 1 could not be located");
  }

  @Test
  void decode_default() {
    response = Response.builder()
        .status(418)
        .reason("Internal server error")
        .request(Request.create(Request.HttpMethod.GET, "", Collections.emptyMap(), null, Util.UTF_8))
        .headers(headers)
        .build();

    ResponseStatusException e = this.feignUtil.decode("", response);
    assertThat(e.getReason()).isEqualTo("Something went wrong");
  }
}