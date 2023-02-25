package com.projtest.api.reqres.testdata;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proj.api.pojos.UserDetails;
import lombok.SneakyThrows;

import java.io.File;

public final class UserTestData {
  private UserTestData() {
  }

  @SneakyThrows
  public static UserDetails getUserDetails() {
    return new ObjectMapper()
      .readValue(new File(System.getProperty("user.dir") + "/src/test/resources/api-resources/requestJsonFiles/post-user.json"), UserDetails.class)
      .setName("Suraj");

  }
}
