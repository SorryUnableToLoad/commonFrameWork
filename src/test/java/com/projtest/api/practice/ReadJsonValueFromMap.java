package com.projtest.api.practice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projtest.api.practice.fileUpload.pojo.Category;
import com.projtest.api.practice.fileUpload.pojo.PetPojo;
import com.projtest.api.practice.fileUpload.pojo.Tag;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ReadJsonValueFromMap {

    @Test
    public void readJsonValueFromMap() throws IOException{
        String filepath = "./src/test/resources/api-resources/requestJsonFiles/postRequestFile.json";
        String stringData = new String(Files.readAllBytes(Paths.get(filepath)));
        //System.out.println(stringData);

        ObjectMapper mapper = new ObjectMapper();
        File jsonFile = new File(filepath);
        PetPojo petPojo = mapper.readValue(jsonFile, PetPojo.class);
        System.out.println("ID : "+petPojo.getId());

        Category category = petPojo.getCategory();
        System.out.println("Category ID : "+category.getId());
        System.out.println("Category Name : "+category.getName());

        System.out.println("Name : "+petPojo.getName());

        String[] photoUrls = petPojo.getPhotoUrls();
        for (String photoUrl:photoUrls) {
            System.out.println(photoUrl);
        }

        Tag[] tags = petPojo.getTags();
        for (Tag tag : tags) {
            System.out.println(tag.getId());
            System.out.println(tag.getName());
        }
        System.out.println("Status : "+petPojo.getStatus());


    }
    @Test
    void test() throws IOException {
        String JSON = "{\"LanguageLevels\":{\"1\":\"Pocz\\u0105tkuj\\u0105cy\",\"2\":\"\\u015arednioZaawansowany\",\"3\":\"Zaawansowany\",\"4\":\"Ekspert\"}}\n";

        String filepath = "./src/test/resources/api-resources/requestJsonFiles/postRequestFile.json";
        String stringData = new String(Files.readAllBytes(Paths.get(filepath)));
        System.out.println(stringData);

        JSONObject obj = new JSONObject(stringData);
        Iterator iterator = obj.keys();
        String key = null;
        while (iterator.hasNext()) {
            key = (String) iterator.next();
            System.out.println(key);
        }
    }

    @Test
    void test1() throws IOException {

        String filepath = "./src/test/resources/api-resources/requestJsonFiles/postRequestFile.json";
        String stringData = new String(Files.readAllBytes(Paths.get(filepath)));
        //System.out.println(stringData);

        String id = getJsonValue(stringData, "id");
        System.out.println(id);

    }
    public static String getJsonValue(String jsonReq, String key) {
        JSONObject json = new JSONObject(jsonReq);
        boolean exists = json.has(key);
        Iterator<?> keys;
        String nextKeys;
        String val = "";
        if (!exists) {
            keys = json.keys();
            while (keys.hasNext()) {
                nextKeys = (String) keys.next();
                try {
                    if (json.get(nextKeys) instanceof JSONObject) {
                        return getJsonValue(json.getJSONObject(nextKeys).toString(), key);
                    } else if (json.get(nextKeys) instanceof JSONArray) {
                        JSONArray jsonArray = json.getJSONArray(nextKeys);
                        int i = 0;
                        if (i < jsonArray.length()) do {
                            String jsonArrayString = jsonArray.get(i).toString();
                            JSONObject innerJson = new JSONObject(jsonArrayString);
                            return getJsonValue(innerJson.toString(),key);
                        } while (i < jsonArray.length());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            val = json.get(key).toString();
        }
        return val;
    }

    private static String readJSONDataFromFile() throws IOException {
        String filepath = "./src/test/resources/api-resources/requestJsonFiles/postRequestFile.json";
        String stringData = new String(Files.readAllBytes(Paths.get(filepath)));
        return stringData;
    }
}
