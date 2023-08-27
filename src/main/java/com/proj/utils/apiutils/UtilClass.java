package com.proj.utils.apiutils;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a simple util class which can be used to log steps for Extent report
 * To use this user have to extend this class and call utilList.add("message");
 * where "message" will be displayed in steps name
 **/
public class UtilClass {

    protected static List<String> utilList = new ArrayList<>();

    UtilClass() {
    }
}

