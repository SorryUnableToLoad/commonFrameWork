package com.proj.test.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proj.apis.Post_or_CreateNewUser;
import com.proj.apis.pojos.UserDetails;
import com.proj.config.factory.ApiConfigFactory;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class Post_or_CreateNewUserTest {
    @Test
    void createNewUser() throws IOException {

        UserDetails userDetails = new ObjectMapper()
                .readValue(new File(System
                        .getProperty("user.dir") + "/src/test/resources/api-resources/post_user.json"), UserDetails.class);
        userDetails.setName("Suraj");

        Response response = Post_or_CreateNewUser.post_or_CreateNewUser(userDetails);

        response.then()
                .assertThat()
                .statusCode(201)
                .time(Matchers.lessThan(ApiConfigFactory.getConfig().responseTime()))
                .contentType(ContentType.JSON);
        response.prettyPrint();
    }
}
