package com.projtest.api.practice.samplepetstore;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class DifferentWayToPostARequest {
    @Test
    public void bodyAsAStringTest() {

        String reqBodyAsString = "{\n" +
                "  \"id\": 102,\n" +
                "  \"category\": {\n" +
                "    \"id\": 11,\n" +
                "    \"name\": \"animal\"\n" +
                "  },\n" +
                "  \"name\": \"cow\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 22,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";

        RequestSpecification requestSpecification = given()
                .baseUri("https://petstore.swagger.io/v2")
                .body(reqBodyAsString)
                .contentType(ContentType.JSON)
                .log().all();

        Response response = requestSpecification
                .post("/pet");

        System.out.println("--------------------------------");

        response.then()
                .assertThat()
                .contentType(ContentType.JSON)
                .log().all();

    }


    @Test
    public void bodyAsExternalFileTest() {

        RequestSpecification requestSpecification = given()
                .baseUri("https://petstore.swagger.io/v2")
                .body(new File("./src/test/resources/api-resources/requestJsonFiles/postRequestFile.json"))
                .contentType(ContentType.JSON)
                .log().all();

        Response response = requestSpecification
                .post("/pet");

        System.out.println("--------------------------------");

        response.then()
                .assertThat()
                .contentType(ContentType.JSON)
                .log().all();
    }

    @Test
    public void bodyAsExternalFile2Test() throws IOException {
        //Converting json file to json string

        String reqBodyObj = new String(Files.readAllBytes(Paths.get("./src/test/resources/api-resources/requestJsonFiles/postRequestFile.json")));
        String replacedBody = reqBodyObj
                .replace("122", String.valueOf(new Faker().number().numberBetween(101, 999)))
                .replace("animalName", new Faker().name().name());

        RequestSpecification requestSpecification = given()
                .baseUri("https://petstore.swagger.io/v2")
                .body(replacedBody)
                .contentType(ContentType.JSON)
                .log().all();

        Response response = requestSpecification
                .post("/pet");

        System.out.println("--------------------------------");

        response.then()
                .assertThat()
                .contentType(ContentType.JSON)
                .log().all();
    }

    @Test
    public void bodyAsMapANDListTest() {
        // using map and list from java
        // { } --> Map interface
        // [ ] --> List Interface
        // serializers --> converts program language objects --> byteStream --> json
        //Here converting map object to json language by using jackson-databind dependency

        Map<String, Object> mapObj = new HashMap<>();
        //****************** Body construction started ***************************//
        mapObj.put("id", 122);

        Map<String, Object> categoryObj = new HashMap<>();
        categoryObj.put("id", 11);
        categoryObj.put("name", "animal");
        mapObj.put("category", categoryObj);

        mapObj.put("name", "animalName");

        List<String> photourlList = new ArrayList<>();
        photourlList.add("string1");
        photourlList.add("String2");
        mapObj.put("photoUrls", photourlList);

        List<Object> tags = new ArrayList<>();

        Map<String, Object> tag1 = new HashMap<>();
        tag1.put("id", 22);
        tag1.put("name", "qwerty");
        Map<String, Object> tag2 = new HashMap<>();
        tag2.put("id", 22);
        tag2.put("name", "qwerty");

        tags.add(tag1);
        tags.add(tag2);

        mapObj.put("tags", tags);
        mapObj.put("status", "available");
        //****************** body construction end ********************//
        Response response = given()
                .body(mapObj)
                .log().body()
                .contentType(ContentType.JSON)
                .post("https://petstore.swagger.io/v2/pet");

        response.prettyPrint();
    }

    @Test
    public void bodyAsJSONObjectTest() {
        // to overcome above approach problems
        // having some collection that can solve the problems we had while using/creating map and list
        // using external json/json-simple library
        // { } --> json object
        // [ ] --> json array
        JSONObject obj = new JSONObject();
        //****************** Body construction started ***************************//
        obj.put("id", 122);

        JSONObject categoryObject = new JSONObject();
        categoryObject.put("id", 11);
        categoryObject.put("name", "animal");
        obj.put("category", categoryObject);

        obj.put("name", "animalname");


        JSONArray photourllist = new JSONArray();
        photourllist.add("string1");
        photourllist.add("String2");
        obj.put("photoUrls", photourllist);

        JSONArray tagss = new JSONArray();

        JSONObject tag1 = new JSONObject();
        tag1.put("id", 22);
        tag1.put("name", "qwerty");
        JSONObject tag2 = new JSONObject();
        tag2.put("id", 22);
        tag2.put("name", "qwerty");

        tagss.add(tag1);
        tagss.add(tag2);

        obj.put("tags", tagss);
        obj.put("status", "available");

        //********* body construction end **************//

        Response response = given()
                .body(obj)
                .log().body()
                .contentType(ContentType.JSON)
                .post("https://petstore.swagger.io/v2/pet");
        System.out.println("---------------Response-----------------");
        response.prettyPrint();

    }
}
