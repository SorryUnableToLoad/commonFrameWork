package sdet.externalfilereading.propertyfile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public final class TryWithResources {
    private static final Map<String, String> CONFIGMAP = new HashMap<>();
    private static Properties pro = new Properties();

    static {
        try (FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/externalfile/prop.properties")) {
            pro.load(fis);
            for (Map.Entry<Object, Object> entry : pro.entrySet()) {
                CONFIGMAP.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()).trim());
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private TryWithResources() {
    }

    public static String get(ConfigProperties key) {
        if (Objects.isNull(key) || Objects.isNull(CONFIGMAP.get(key.name().toLowerCase()))) {
            throw new RuntimeException(" Property Name " + key + " is not found. Please check config.properties");
        }
        return CONFIGMAP.get(key.name().toLowerCase());

    }

    public static String get(String key) {
        if (Objects.isNull(key) || Objects.isNull(CONFIGMAP.get(key.toLowerCase()))) {
            throw new RuntimeException(" Property Name " + key + " is not found. Please check config.properties");
        }
        return CONFIGMAP.get(key.toLowerCase());
    }

}
