package com.projtest.api.practice.reporttest;


import com.proj.annotations.FrameworkAnnotations;
import com.proj.utils.apiutils.ApiUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static com.proj.config.factory.ApiConfigFactory.getConfig;
import static io.restassured.RestAssured.given;

//@Listeners(com.proj.listeners.TestListenerImpl.class)
public class PostResourceTest {
    @FrameworkAnnotations(author = "Suraj")
    @Test
    public void postResource() {
        String resource = ApiUtils.readJsonAndGetAsString("./src/test/resources/requestJsonFiles/postRequestFile.json");

        RequestSpecification requestSpecification = given()
                .body(resource)
                .contentType(ContentType.JSON);

        //ExtentReport.logRequest(requestSpecification);

        Response response = requestSpecification.post(getConfig().apibaseurl() + getConfig().addNewPetStore());

        response.then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .time(Matchers.lessThan(400L), TimeUnit.SECONDS)
                .log().all();
        //ExtentReport.logResponse(response.asPrettyString());


    }
}
