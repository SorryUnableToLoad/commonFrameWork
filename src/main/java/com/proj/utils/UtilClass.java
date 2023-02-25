package com.proj.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a simple util class which can be used to log steps for Extent report
 * To use this user have to extend this class and call utilList.add("message");
 * where "message" will be displayed in steps name
 *
 * @author nishanth.t
 *
 */
public class UtilClass {

    public static List<String> utilList = new ArrayList<String>();

}

