package com.projtest.api.practice.fileUpload;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projtest.api.practice.fileUpload.pojo.PetPojo;
import com.projtest.api.practice.fileUpload.pojo.Tag;
import com.projtest.api.practice.fileUpload.pojo.Category;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class FileUploadTest {
    @Test
    public void uploadPetImageTest() {
        Category category = new Category(123, "Dog");
        String[] photoUrls = {"url1", "url2"};
        Tag[] tags = new Tag[]{new Tag(12, "Dog")};
        PetPojo petObj = new PetPojo(13, category, "Dogie", photoUrls, tags, "Available");

        Response response = RestAssured.given()
                .body(petObj)
                .contentType(ContentType.JSON)
                .baseUri("https://petstore.swagger.io/v2")
                .when()
                .post("/pet");
        response.then()
                .log().all();

        int petId = response.jsonPath().get("id");
        System.out.println("Pet Id from response: " + petId);

        RestAssured.given()
                .baseUri("https://petstore.swagger.io/v2")
                .pathParam("petId", petId)
                .multiPart(new File("./src/test/resources/api-resources/uploadFiles/406*503.jpeg"))
                .when()
                .post("/pet/{petId}/uploadImage")
                .then()
                .log().all();
    }
}
