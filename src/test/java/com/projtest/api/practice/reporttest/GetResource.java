package com.projtest.api.practice.reporttest;

import com.proj.annotations.FrameworkAnnotations;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

//@Listeners(com.proj.listeners.TestListenerImpl.class)
public class GetResource {
    @FrameworkAnnotations(author = "Suraj")
    @Test
    public void getResourceTest() {
        int petId = 122;

        Response response = given()
                .pathParam("petID", petId)
                .get("https://petstore.swagger.io/v2/pet/{petID}");

        response.prettyPrint();

        response.then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .log().all();
        //ExtentReport.logResponse(response.asPrettyString());


    }
}
