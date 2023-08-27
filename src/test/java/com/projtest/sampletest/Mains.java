package com.projtest.sampletest;

import com.proj.utils.JsonUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static com.proj.utils.JsonUtils.*;


public class Mains {
    public static void main(String[] args) throws IOException {
        String filepath = "./src/test/resources/jsonData.json";
        String jsonData = new String(Files.readAllBytes(Paths.get(filepath)));

        System.out.println(prettyPrint(jsonData));
        System.out.println("--------------------------------");

        String name = extractValueFromJson(jsonData, "name");
        System.out.println("Name -> : " + name);
        System.out.println("--------------------------------");

        Object street = extractValueFromJson(jsonData, "address", "street");
        System.out.println("Street -> : " + street);
        System.out.println("--------------------------------");

        String d = extractValueFromJson(jsonData, "data", "items", "id");
        System.out.println("d -> : " + d);
        System.out.println("--------------------------------");

        String d1 = extractValueFromJson(jsonData, 1, "data", "items", "id");
        System.out.println("d1 -> : " + d1);
        System.out.println("--------------------------------");

        String[] skills = extractValuesFromJsonArray(jsonData, "skills");
        System.out.println("Extracted Skills -> :");
        for (String skill : skills) {
            System.out.println(skill);
        }
        System.out.println("--------------------------------");

        String[] ids = extractValuesFromJsonArray(jsonData, "phoneNumbers", "id");
        System.out.println("Extracted phoneNumbers id -> :");
        for (String id : ids) {
            System.out.println(id);
        }
        System.out.println("--------------------------------");

        String[] types = extractValuesFromJsonArray(jsonData, "phoneNumbers", "type");
        System.out.println("Extracted phoneNumbers Types -> :");
        for (String type : types) {
            System.out.println(type);
        }
        System.out.println("--------------------------------");

        String[] numbers = extractValuesFromJsonArray(jsonData, "phoneNumbers", "number");
        System.out.println("Extracted phoneNumbers Numbers -> :");
        for (String number : numbers) {
            System.out.println(number);
        }
        System.out.println("--------------------------------");

        Map<String, String> extractedValues = extractValuesFromJson(jsonData, "name", "city", "id", "skills");
        System.out.println("Extracted Values from map -> :");
        System.out.println(extractedValues.get("name"));
        System.out.println(extractedValues.get("address.city"));
        System.out.println(extractedValues.get("data.id"));
        System.out.println(extractedValues.get("data.items.id"));
        System.out.println(extractedValues.get("phoneNumbers.id"));
        System.out.println("**************");
        System.out.println(extractedValues.get("skills"));
        System.out.println("**************");
        for (Map.Entry<String, String> entry : extractedValues.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        System.out.println("--------------------------------");

        String[] itemsId = JsonUtils.extractValuesFromJsonArray(extractValueFromJson(jsonData, "data"), "items", "id");
        for (String value : itemsId) {
            System.out.println("5.Extracted value: " + value);
        }

        String[] keys = {"data", "items"};
        String valueKey = "id";
        String[] value = extractValuesFromNestedJsonArray(jsonData, keys, valueKey);
        for (String values : value) {
            System.out.println("6.Extracted value: " + values);
        }

    }

    /*public static Map<String, String> extractValuesFromJson(String jsonData, String... keys) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> extractedValues = new HashMap<>();
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonData);
            for (String key : keys) {
                String value = extractValue(jsonNode, key);
                extractedValues.put(key, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return extractedValues;
    }

    private static String extractValue(JsonNode jsonNode, String key) {
        JsonNode valueNode = jsonNode.get(key);
        if (valueNode != null && valueNode.isValueNode()) {
            return valueNode.asText();
        } else if (valueNode != null) {
            return valueNode.toString();
        }
        return null;
    }*/

}
