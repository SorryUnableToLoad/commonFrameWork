package com.projtest.api.fileUpload;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projtest.api.fileUpload.pojo.PetPojo;
import com.projtest.api.fileUpload.pojo.Tag;
import com.projtest.api.fileUpload.pojo.Category;
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
                .multiPart(new File("./src/test/resources/Rainbow Trout.jpeg"))
                .when()
                .post("/pet/{petId}/uploadImage")
                .then()
                .log().all();
    }

    @Test
    public void validatePojoToJson() throws IOException {
        Category category = new Category(123, "Dog");
        String[] photoUrls = {"url1", "url2"};
        Tag[] tags = new Tag[]{new Tag(12, "Dog")};
        PetPojo petObj = new PetPojo(12, category, "Dogie", photoUrls, tags, "Available");

        ObjectMapper map = new ObjectMapper();
        String obj = map.writeValueAsString(petObj);
        System.out.println(obj);
    }
}
