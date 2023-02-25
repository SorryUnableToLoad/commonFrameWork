package com.projtest.api.practice.reporttest;


import com.proj.common.annotations.FrameworkAnnotations;
import com.proj.utils.apiutils.ApiUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static com.proj.common.config.factory.ApiConfigFactory.getConfig;
import static io.restassured.RestAssured.given;

public class PostResource {
    private static final String BASE_URI=getConfig().apiBaseurl();
    private static final String ADD_NEW_PETSTORE=getConfig().addNewPetStore();

    String filePath="./src/test/resources/api-resources/requestJsonFiles/postRequestFile.json";

    @FrameworkAnnotations(author = "Suraj")
    @Test
    public void postResourceTest() {

        String resource = ApiUtils.readJsonAndGetAsString(filePath)
                .replace("122","255")
                .replace("string","fox");

        RequestSpecification requestSpecification = given()
                .baseUri(BASE_URI)
                .body(resource)
                .contentType(ContentType.JSON)
                .log().all();

        //ExtentReport.logRequest(requestSpecification);

        Response response = requestSpecification
                .post(ADD_NEW_PETSTORE);



        response.then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .time(Matchers.lessThan(400L), TimeUnit.SECONDS)
                .log().all();
        //ExtentReport.logResponse(response.asPrettyString());


    }
}
