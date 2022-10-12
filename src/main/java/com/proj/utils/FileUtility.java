package com.proj.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * This Class Provides generic method to read data from property file
 * @author surajkumarnaganuri
 */
public class FileUtility {
    private FileUtility(){
    }
    static FileInputStream fis;
    public static String readDataFromPropertyFile(String key){
        try {
            fis = new FileInputStream("path");
            Properties pro = new Properties();
            pro.load(fis);
            return pro.getProperty(key);
        } catch (Exception e) {
            System.out.println("Exception is " +e.getMessage());
        }finally {
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
