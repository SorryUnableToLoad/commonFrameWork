package com.projtest.api.practice.reporttest;

import com.proj.annotations.FrameworkAnnotations;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

//@Listeners(com.proj.listeners.TestListenerImpl.class)
public class GetResource {
    /*
     * There should be a test case matching this test name in RUNMANAGER and TESTDATA sheet
     * If there are multiple data lines in TESTDATA sheet, it will treated as iteration
     * Mark Yes in RUNMANAGER and TESTDATA to run this test case
     * @author Surajkumar N
     */
    @FrameworkAnnotations(author = "Suraj")
    @Test
    public void getResource() {
        int petid = 122;

        Response responce = given()
                .pathParam("petID", petid)
                .get("https://petstore.swagger.io/v2/pet/{petID}");

        responce.prettyPrint();

        responce.then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .log().all();
        //ExtentReport.logResponse(responce.asPrettyString());


    }
}
