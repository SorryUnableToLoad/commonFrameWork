package externalfilereading;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public final class PropertyFileUtils {
    private PropertyFileUtils() {
    }

    public static String readDataFromPropertyFile(String key) {
        FileInputStream fis = null;
        Properties pro;
        try {
            fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/prop.properties");
            pro = new Properties();
            pro.load(fis);
            return pro.getProperty(key);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Exception is " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return key;
    }
}