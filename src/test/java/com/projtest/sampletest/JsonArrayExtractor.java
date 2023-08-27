package com.projtest.sampletest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonArrayExtractor {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        String filepath = "./src/test/resources/jsonData.json";
        String jsonData = new String(Files.readAllBytes(Paths.get(filepath)));
        String[] keys = {"data", "items"};
        String valueKey = "id";

        String[] extractedValues = extractValuesFromNestedJsonArray(jsonData, keys, valueKey);

        for (String value : extractedValues) {
            System.out.println("Extracted value: " + value);
        }
    }

    public static String[] extractValuesFromJsonArray(JsonNode arrayNode, String valueKey) {
        if (arrayNode != null && arrayNode.isArray()) {
            int size = arrayNode.size();
            String[] values = new String[size];
            for (int i = 0; i < size; i++) {
                JsonNode objectNode = arrayNode.get(i);
                if (objectNode != null) {
                    JsonNode valueNode = objectNode.get(valueKey);
                    if (valueNode != null) {
                        values[i] = valueNode.asText();
                    }
                }
            }
            return values;
        }
        return new String[0];
    }

    public static String[] extractValuesFromNestedJsonArray(String jsonData, String[] keys, String valueKey) {
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonData);
            JsonNode currentNode = jsonNode;

            for (String key : keys) {
                currentNode = currentNode.get(key);
                if (currentNode == null) {
                    return new String[0]; // Key not found, return an empty array
                }
            }

            return extractValuesFromJsonArray(currentNode, valueKey);
        } catch (IOException e) {
            e.printStackTrace();
            return new String[0];
        }
    }
}

