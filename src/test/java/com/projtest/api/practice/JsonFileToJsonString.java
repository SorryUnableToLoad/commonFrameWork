package com.projtest.api.practice;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonFileToJsonString {
    public static void main(String[] args) throws IOException {
        String filepath = "./src/test/resources/api-resources/requestJsonFiles/postRequestFile.json";
        System.out.println(new String(Files.readAllBytes(Paths.get(filepath))));
    }

}
