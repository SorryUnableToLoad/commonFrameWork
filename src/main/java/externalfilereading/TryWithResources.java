package externalfilereading;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public final class TryWithResources {
    private TryWithResources() {
    }

    private static Properties pro = new Properties();
    private static final Map<String, String> CONFIGMAP = new HashMap<>();

    static {
        try (FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/prop.properties")) {
            pro.load(fis);
            for (Map.Entry<Object, Object> entry : pro.entrySet()) {
                CONFIGMAP.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()).trim());
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static String get(ConfigProperties key) {
       if (Objects.isNull(key) || Objects.isNull(CONFIGMAP.get(key.name().toLowerCase()))) {
            throw new RuntimeException(" Property Name " + key + " is not found. Please check config.properties");
        }
        return CONFIGMAP.get(key.name().toLowerCase());

    }
    public static String get(String key){
        if (Objects.isNull(key) || Objects.isNull(CONFIGMAP.get(key.toLowerCase()))) {
            throw new RuntimeException(" Property Name " + key + " is not found. Please check config.properties");
        }
        return CONFIGMAP.get(key.toLowerCase());
    }

}
