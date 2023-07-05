package com.proj.utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;

public class JsonUtils {

    private static ObjectMapper objectMapper = getDefaultObjectMapper();

    private static ObjectMapper getDefaultObjectMapper() {
        ObjectMapper defaultObjectMapper = new ObjectMapper();
        defaultObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return defaultObjectMapper;
    }

    public static JsonNode parse(String src) {
        JsonNode jsonNode = null;
       /*try {
            return objectMapper.readTree(src);
        } catch (JsonParseException e) {
            e.printStackTrace();
        }*/

        try {
            jsonNode = objectMapper.readTree(src);
            // Do something with the jsonNode object
        } catch (JsonParseException e) {
            System.err.println("Error: Invalid JSON string");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error: Failed to parse JSON string");
            e.printStackTrace();
        }
        return jsonNode;
    }

    public static JsonNode parse(String src, String path) {
        JsonNode rootNode = null;
        try {
            rootNode = objectMapper.readTree(src);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return rootNode.at(path);
    }

    public static <A> A fromJson(JsonNode node, Class<A> clazz) {
        try {
            return objectMapper.treeToValue(node, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    public static <A> A fromJson(String res, Class<A> clazz) {
        return fromJson(JsonUtils.parse(res), clazz);
    }

    public static JsonNode toJson(Object obj) {
        return objectMapper.valueToTree(obj);
    }

    public static String stringify(JsonNode node) {
        ObjectWriter objectWriter = objectMapper.writer();
        try {
            return objectWriter.writeValueAsString(node);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String stringify(Object jsonPojo) {
        JsonNode node = JsonUtils.toJson(jsonPojo);
        ObjectWriter objectWriter = objectMapper.writer();
        try {
            return objectWriter.writeValueAsString(node);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String prettyPrint(Object o) {
        ObjectWriter objectWriter = objectMapper.writer()
                .with(SerializationFeature.INDENT_OUTPUT);
        try {
            return objectWriter.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public static String prettyPrint(JsonNode node) {
        ObjectWriter objectWriter = objectMapper.writer()
                .with(SerializationFeature.INDENT_OUTPUT);
        try {
            return objectWriter.writeValueAsString(node);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public static String prettyPrint(String response) {
        JsonNode parsedJson = JsonUtils.parse(response);

        ObjectWriter objectWriter = objectMapper.writer()
                .with(SerializationFeature.INDENT_OUTPUT);
        try {
            return objectWriter.writeValueAsString(parsedJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
