package com.proj.test.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proj.annotations.FrameworkAnnotations;
import com.proj.apis.Post_or_CreateNewUser;
import com.proj.apis.pojos.UserDetails;
import com.proj.config.factory.ApiConfigFactory;
import com.proj.listeners.extentreport.ExtentReport;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static com.proj.config.factory.ApiConfigFactory.getConfig;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
@Listeners(com.proj.listeners.TestListenerImpl.class)
public class Post_or_CreateNewUserTest {
    @FrameworkAnnotations(author = "Suraj")
    @Test
    void createNewUser() throws IOException {

        UserDetails userDetails = new ObjectMapper()
                .readValue(new File(System
                        .getProperty("user.dir") + "/src/test/resources/api-resources/post_user.json"), UserDetails.class);
        userDetails.setName("Suraj");

        RequestSpecification requestSpecification = given()
                .baseUri(getConfig().apibaseurl())
                .body(userDetails)
                .contentType(ContentType.JSON);
        ExtentReport.logRequest(requestSpecification);

        Response response = requestSpecification.when().post(getConfig().postUserEndPoint());

        response.then()
                .assertThat()
                .statusCode(201)
                .time(Matchers.lessThan(getConfig().responseTime()))
                .contentType(ContentType.JSON);
        ExtentReport.logResponse(response.prettyPrint());













        /*Response response = Post_or_CreateNewUser.post_or_CreateNewUser(userDetails);

        response.then()
                .assertThat()
                .statusCode(201)
                .time(Matchers.lessThan(getConfig().responseTime()))
                .contentType(ContentType.JSON);
        ExtentReport.logResponse(response.prettyPrint());*/
    }
}
