package com.proj.common.listeners.extentreport;

import com.aventstack.extentreports.ExtentTest;

public class ExtentManager {
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    private ExtentManager() {
    }

    public static ExtentTest getTest() {
        return extentTest.get();
    }

    static void setExtent(ExtentTest test) {
        extentTest.set(test);
    }
}
