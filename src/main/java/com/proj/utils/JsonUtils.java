package com.proj.utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.proj.utils.apiutils.LoggerUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JsonUtils {

    private static final ObjectMapper objectMapper = getDefaultObjectMapper();

    private static ObjectMapper getDefaultObjectMapper() {
        ObjectMapper defaultObjectMapper = new ObjectMapper();
        defaultObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return defaultObjectMapper;

    }

    public static JsonNode parse(String src) {
        try {
            return objectMapper.readTree(src); // Return the non-null jsonNode
        } catch (JsonParseException e) {
            LoggerUtils.error("Error: Invalid JSON string");
            e.printStackTrace();
        } catch (IOException e) {
            LoggerUtils.error("Error: Failed to parse JSON string");
            e.printStackTrace();
        }
        // If an exception was caught, return a default or empty JsonNode
        return JsonNodeFactory.instance.nullNode();
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
        ObjectWriter objectWriter = objectMapper.writer().with(SerializationFeature.INDENT_OUTPUT);
        try {
            return objectWriter.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public static String prettyPrint(JsonNode node) {
        ObjectWriter objectWriter = objectMapper.writer().with(SerializationFeature.INDENT_OUTPUT);
        try {
            return objectWriter.writeValueAsString(node);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public static String prettyPrint(String s) {
        JsonNode parsedJson = parse(s);

        ObjectWriter objectWriter = objectMapper.writer().with(SerializationFeature.INDENT_OUTPUT);
        try {
            return objectWriter.writeValueAsString(parsedJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public static String extractValueFromJson(String jsonData, String nodeKey) {
        JsonNode jsonNode = parse(jsonData);
        JsonNode valueNode = jsonNode.get(nodeKey);
        if (valueNode != null && valueNode.isValueNode()) {
            return valueNode.asText();
        } else if (valueNode != null) {
            return valueNode.toString();
        }
        return null;
    }

    public static Object extractValueFromJson(String jsonData, String rootNodeKey, String nodeKey) {
        JsonNode jsonNode = parse(jsonData);
        JsonNode rootNode = jsonNode.get(rootNodeKey);
        if (rootNode != null && rootNode.isObject()) {
            JsonNode valueNode = rootNode.get(nodeKey);
            if (valueNode != null) {
                return valueNode.asText();
            }
        }
        return null;

    }

    public static String extractValueFromJson(String jsonData, String rootNodeKey, String subRootNodeKey, String nodeKey) {
        JsonNode jsonNode = parse(jsonData);
        JsonNode rootNode = jsonNode.get(rootNodeKey);
        if (rootNode.isArray() && !rootNode.isEmpty()) {
            JsonNode subRootNode = rootNode.get(0).get(subRootNodeKey);
            if (subRootNode != null) {
                return subRootNode.get(nodeKey).asText();
            }
        } else if (rootNode.isObject()) {
            JsonNode subDataNode = rootNode.path(subRootNodeKey).get(0);
            return subDataNode.path(nodeKey).asText();
        }
        return null;
    }

    public static String extractValueFromJson(String jsonData, int index, String rootNodeKey, String subRootNodeKey, String nodeKey) {
        JsonNode jsonNode = parse(jsonData);
        JsonNode rootNode = jsonNode.get(rootNodeKey);
        if (rootNode.isArray() && !rootNode.isEmpty()) {
            JsonNode subRootNode = rootNode.get(index).get(subRootNodeKey);
            if (subRootNode != null) {
                return subRootNode.get(nodeKey).asText();
            }
        } else if (rootNode.isObject()) {
            JsonNode subDataNode = rootNode.path(subRootNodeKey).get(index);
            return subDataNode.path(nodeKey).asText();
        }
        return null;
    }

    public static String[] extractValuesFromJsonArray(String jsonData, String arrayKey) {
        JsonNode jsonNode = parse(jsonData);
        JsonNode arrayNode = jsonNode.get(arrayKey);
        if (arrayNode != null && arrayNode.isArray()) {
            int size = arrayNode.size();
            String[] values = new String[size];
            for (int i = 0; i < size; i++) {
                JsonNode valueNode = arrayNode.get(i);
                if (valueNode != null) {
                    values[i] = valueNode.asText();
                }
            }
            return values;
        }
        return new String[0];
    }

    public static String[] extractValuesFromJsonArray(String jsonData, String arrayKey, String valueKey) {
        JsonNode jsonNode = parse(jsonData);
        JsonNode arrayNode = jsonNode.get(arrayKey);
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
        JsonNode jsonNode = parse(jsonData);
        for (String key : keys) {
            jsonNode = jsonNode.get(key);
            if (jsonNode == null) {
                return new String[0]; // Key not found, return an empty array
            }
        }

        if (jsonNode.isArray()) {
            int size = jsonNode.size();
            String[] values = new String[size];
            for (int i = 0; i < size; i++) {
                JsonNode objectNode = jsonNode.get(i);
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

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    public static Map<String, String> extractValuesFromJson(String jsonData, String... keys) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> resultMap = new HashMap<>();
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonData);
            for (String key : keys) {
                extractValue(jsonNode, key, "", resultMap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    private static void extractValue(JsonNode jsonNode, String key, String path, Map<String, String> resultMap) {
        if (jsonNode.isObject()) {
            JsonNode node = jsonNode.get(key);
            if (node != null) {
                resultMap.put(path + key, node.asText());
            }
            Iterator<String> fieldNames = jsonNode.fieldNames();
            while (fieldNames.hasNext()) {
                String fieldName = fieldNames.next();
                JsonNode fieldNode = jsonNode.get(fieldName);
                extractValue(fieldNode, key, path + fieldName + ".", resultMap);
            }
        } else if (jsonNode.isArray()) {
            for (JsonNode node : jsonNode) {
                extractValue(node, key, path, resultMap);
            }
        }
    }

    private JsonUtils() {
    }


    /*public static Map<String, String> extractValuesFromJson(String jsonData, String key) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> resultMap = new HashMap<>();
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonData);
            extractValue(jsonNode, key, "", resultMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    private static void extractValue(JsonNode jsonNode, String key, String path, Map<String, String> resultMap) {
        if (jsonNode.isObject()) {
            JsonNode node = jsonNode.get(key);
            if (node != null) {
                resultMap.put(path + key, node.asText());
            }
            Iterator<String> fieldNames = jsonNode.fieldNames();
            while (fieldNames.hasNext()) {
                String fieldName = fieldNames.next();
                JsonNode fieldNode = jsonNode.get(fieldName);
                extractValue(fieldNode, key, path + fieldName + ".", resultMap);
            }
        } else if (jsonNode.isArray()) {
            for (JsonNode node : jsonNode) {
                extractValue(node, key, path, resultMap);
            }
        }
    }*/

   /* public static List<Object> extractValuesFromJson(String jsonData, String key) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonData);
            return extractValue(jsonNode, key, "");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    private static List<Object> extractValue(JsonNode jsonNode, String key, String path) {
        List<Object> values = new ArrayList<>();
        if (jsonNode.isObject()) {
            JsonNode node = jsonNode.get(key);
            if (node != null) {
                values.add(node.asText());
            }
            Iterator<String> fieldNames = jsonNode.fieldNames();
            while (fieldNames.hasNext()) {
                String fieldName = fieldNames.next();
                JsonNode fieldNode = jsonNode.get(fieldName);
                values.addAll(extractValue(fieldNode, key, path + fieldName + "."));
            }
        } else if (jsonNode.isArray()) {
            for (JsonNode node : jsonNode) {
                values.addAll(extractValue(node, key, path));
            }
        }
        return values;
    }*/

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
   /* public static void extractValueFromJson(String jsonData, String key) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonData);
            extractValue(jsonNode, key, "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void extractValue(JsonNode jsonNode, String key, String path) {
        if (jsonNode.isObject()) {
            JsonNode node = jsonNode.get(key);
            if (node != null) {
                System.out.println(path + key + ": " + node.asText());
            }
            Iterator<String> fieldNames = jsonNode.fieldNames();
            while (fieldNames.hasNext()) {
                String fieldName = fieldNames.next();
                JsonNode fieldNode = jsonNode.get(fieldName);
                extractValue(fieldNode, key, path + fieldName + ".");
            }
        } else if (jsonNode.isArray()) {
            for (JsonNode node : jsonNode) {
                extractValue(node, key, path);
            }
        }
    }*/

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
   /* public static void extractValueFromJson(String jsonData, String... keys) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonData);
            extractValues(jsonNode, keys, "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void extractValues(JsonNode jsonNode, String[] keys, String path) {
        if (jsonNode.isObject()) {
            for (String key : keys) {
                JsonNode node = jsonNode.get(key);
                if (node != null) {
                    extractValue(node, path + key);
                }
            }
            Iterator<String> fieldNames = jsonNode.fieldNames();
            while (fieldNames.hasNext()) {
                String fieldName = fieldNames.next();
                JsonNode fieldNode = jsonNode.get(fieldName);
                extractValues(fieldNode, keys, path + fieldName + ".");
            }
        } else if (jsonNode.isArray()) {
            for (JsonNode node : jsonNode) {
                extractValues(node, keys, path);
            }
        }
    }

    private static void extractValue(JsonNode jsonNode, String path) {
        if (jsonNode.isValueNode()) {
            String value = jsonNode.asText();
            System.out.println(path + ": " + value);
        } else if (jsonNode.isObject() || jsonNode.isArray()) {
            Iterator<String> fieldNames = jsonNode.fieldNames();
            while (fieldNames.hasNext()) {
                String fieldName = fieldNames.next();
                JsonNode fieldNode = jsonNode.get(fieldName);
                extractValue(fieldNode, path + fieldName + ".");
            }
        }
    }*/

}