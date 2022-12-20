package com.projtest.api;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proj.annotations.FrameworkAnnotations;
import com.proj.api.pojos.UserDetails;
import com.proj.listeners.extentreport.ExtentManager;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static com.proj.config.factory.ApiConfigFactory.getConfig;
import static io.restassured.RestAssured.given;


public class Post_or_CreateNewUserTest {

    @Test
    @FrameworkAnnotations(author = "Suraj")
    public void createNewUser() throws IOException {

        UserDetails userDetails = new ObjectMapper()
                .readValue(new File(System
                        .getProperty("user.dir") + "/src/test/resources/api-resources/post_user.json"), UserDetails.class);
        userDetails.setName("Suraj");


        RequestSpecification requestSpecification = given()
                .baseUri(getConfig().apibaseurl())
                .body(userDetails)
                .contentType(ContentType.JSON);
        //ExtentReport.logRequest(requestSpecification);

        Response response = requestSpecification.when().post(getConfig().postUserEndPoint());

        response.then()
                .assertThat()
                .statusCode(201)
                .time(Matchers.lessThan(getConfig().responseTime()))
                .contentType(ContentType.JSON);
        //ExtentReport.logResponse(response.prettyPrint());

        /*Response response = Post_or_CreateNewUser.post_or_CreateNewUser(userDetails);

        response.then()
                .assertThat()
                .statusCode(201)
                .time(Matchers.lessThan(getConfig().responseTime()))
                .contentType(ContentType.JSON);
        ExtentReport.logResponse(response.prettyPrint());*/
    }

    public static void logRequest(RequestSpecification requestSpecification) {
        QueryableRequestSpecification query = SpecificationQuerier.query(requestSpecification);

        ExtentManager.getTest().log(Status.INFO, "Request Body Details");

        ExtentManager.getTest().pass(MarkupHelper.createCodeBlock(query.getBody(), CodeLanguage.JSON));
        for (Header header : query.getHeaders()) {
            ExtentManager.getTest().log(Status.INFO, header.getName() + ":" + header.getValue());
        }
    }

    public static void logResponse(String response) {
        ExtentManager.getTest().log(Status.INFO, "Response Body Details");
        ExtentManager.getTest().pass(MarkupHelper.createCodeBlock(response, CodeLanguage.JSON));
    }
}
