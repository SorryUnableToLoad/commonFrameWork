package com.projtest.api.practice.samplepetstore;

import com.projtest.api.practice.fileUpload.pojo.Category;
import com.projtest.api.practice.fileUpload.pojo.PetPojo;
import com.projtest.api.practice.fileUpload.pojo.Tag;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;
import java.util.SortedMap;

import static io.restassured.RestAssured.given;

public class PostRequestUsingPojoClassAndSchemaValidation {

    // [] --> List type
    // {} --> class file
    // plain old java object
    @Test
    public void PostRequestUsingPojoTest() {
        //Request body construction //
        Category category = new Category(12, "animal");

        String[] photoUrls = {"url1", "url2", "url3"};

        Tag tag1 = new Tag(1, "name1");
        Tag tag2 = new Tag(2, "name2");
        Tag[] tags = {tag1, tag2};

        PetPojo petPojo = new PetPojo(225,category,"Fox",photoUrls,tags,"Available");

        RequestSpecification requestSpecification = given()
                .body(petPojo)
                .contentType(ContentType.JSON)
                .log().all();

        Response response = requestSpecification.post("https://petstore.swagger.io/v2/pet");

        response.then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json")
                .log().all();

        System.out.println("Category Name : " + response.jsonPath().getString("category.name"));
        System.out.println("PhotoUrls : "+response.jsonPath().getString("photoUrls"));
        System.out.println("Tag Name : "+response.jsonPath().getString("tags[0].name"));


        PetPojo deserializePetStorePojo = response.as(PetPojo.class);
        Category categoryS = deserializePetStorePojo.getCategory();
        System.out.println("Category Id : "+categoryS.getId());
        System.out.println("Category Name : "+categoryS.getName());

        String[] photoUrl = deserializePetStorePojo.getPhotoUrls();
        for (String url:photoUrl) {
            System.out.println("PhotoUrls : "+url);
        }
        Tag[] tagsS = deserializePetStorePojo.getTags();
        for (Tag tag : tagsS) {
            System.out.println("Tag Id : "+tag.getId());
            System.out.println("Tag Name : "+tag.getName());
        }


        response.then()
                .body(JsonSchemaValidator.matchesJsonSchema(new File("./src/test/resources/api-resources/schemaJsonFiles/schema.json")));
    }
}
