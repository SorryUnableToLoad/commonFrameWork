package com.apiproj.utils;

import org.apache.commons.collections.map.HashedMap;
import org.testng.Assert;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

/**
 * Simple property utils class which can be used to read and write from a
 * property file user just needs to give file path of property file located.
 *
 * @author nishanth.t
 *
 */
public class PropertyUtils {
    private Properties props = new Properties();
    private Map<String, String> map = new HashedMap();

    public PropertyUtils(String path) {

        loadPropertyFile(path);
        createMap(path);
    }

    public void loadPropertyFile(String propertyFileName) {
        try {
            props.load(new FileInputStream(propertyFileName));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            Assert.fail("Unable to load file!", e);
            // e.printStackTrace();
        }
    }

    private void createMap(String path){
        Scanner scan = null;
        try {
            scan = new Scanner(new File(path));
            while(scan.hasNextLine()) {
                String split[] = scan.nextLine().trim().split("=");
                if(split.length == 2) {
                    map.put(split[0].trim(), split[1].trim());
                }
            }
        } catch (FileNotFoundException e) {
            Assert.fail("Unable to scan the  file!", e);
        }
    }

    public Map<String, String> getKeyValueMapFromPropertyFile(){
        return map;
    }

    public String getProperty(String propertyKey) {
        String propertyValue = props.getProperty(propertyKey.trim());

        if (propertyValue == null || propertyValue.trim().length() == 0) {
            // Log error message
        }

        return propertyValue;
    }

    public void setProperty(String propertyKey, String value) {
        props.setProperty(propertyKey, value);
    }
}