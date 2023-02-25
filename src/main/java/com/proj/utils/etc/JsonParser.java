package com.proj.utils.etc;

import com.jayway.jsonpath.JsonPath;
import com.proj.common.pathconstants.IConstants;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;


public class JsonParser {
    private static File jsonFile;

    public static String getValue(String path) throws IOException {
        String temp = "";
        try {
            jsonFile = new File(IConstants.JSONPATH);
            temp = JsonPath.read(jsonFile, "$." + path);
        } catch (Exception e) {

        }
        return temp;

    }

    @Test
    void test() throws IOException {
        String value = JsonParser.getValue("config.global.appiumurl");
        System.out.println(value);
    }
}
