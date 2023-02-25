package com.projtest.api.practice.samplepetstore;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;

import static io.restassured.RestAssured.given;
import static java.nio.file.Paths.get;

public class GetRequest_And_StoreInExternalFile {
    @Test
    public void getResource() throws IOException {
        int petId = 225;

        Response response = given()
                .pathParam("petID", petId)
                .log()
                .all()
                .get("https://petstore.swagger.io/v2/pet/{petID}");

        response.prettyPrint();

        Files.write(get("./src/test/resources/api-resources/jsonFiles/response.json"), response.asByteArray());
    }
}
