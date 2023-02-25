package com.projtest.api.practice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;


public class JsonStringToHashMap {
    public static void main(String[] args) throws IOException {
        String json = "{\r\n\"name\" : \"abc\" ,\r\n\"email id \" : [\"abc@gmail.com\",\"def@gmail.com\",\"ghi@gmail.com\"]\r\n}";

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map;
        // convert JSON string to Map
        map = mapper.readValue(json, new TypeReference<>() {});
        System.out.println(map);

        System.out.println("----------------------------------------------------------------");

        String filepath = "./src/test/resources/api-resources/requestJsonFiles/postRequestFile.json";
        String stringData = new String(Files.readAllBytes(Paths.get(filepath)));
        //System.out.println(stringData);

        ObjectMapper mapper1 = new ObjectMapper();
        Map<String, Object> map1;
        // convert JSON string to Map
        map1 = mapper1.readValue(stringData, new TypeReference<>() {});
        System.out.println(map1);

    }

}
