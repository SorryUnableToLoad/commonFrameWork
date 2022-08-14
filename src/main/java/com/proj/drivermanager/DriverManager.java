package com.proj.drivermanager;

import com.proj.enums.PlatFormType;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebDriver;

import java.util.EnumMap;
import java.util.Map;

import static com.proj.enums.PlatFormType.*;
import static java.lang.ThreadLocal.*;


public class DriverManager {
    private DriverManager(){}

    private static final ThreadLocal<WebDriver> WEB_DRIVER_THREAD_LOCAL = new ThreadLocal<>();
    private static final ThreadLocal<WebDriver> MOBILE_DRIVER_THREAD_LOCAL = new ThreadLocal<>();
    private static final ThreadLocal<PlatFormType> CONTEXT = withInitial(()-> WEB);
    private static final Map<PlatFormType,ThreadLocal<WebDriver>> DRIVER_MAP = new EnumMap<>(PlatFormType.class);

    public static WebDriver getDriver(){
        return CONTEXT.get() == WEB
                ? WEB_DRIVER_THREAD_LOCAL.get()
                : MOBILE_DRIVER_THREAD_LOCAL.get();
    }

    public static void setDriver(WebDriver driver){
        if(isMobileDriver(driver)){
            MOBILE_DRIVER_THREAD_LOCAL.set(driver);
            DRIVER_MAP.put(MOBILE,MOBILE_DRIVER_THREAD_LOCAL);
            CONTEXT.set(MOBILE);
        } else {
            WEB_DRIVER_THREAD_LOCAL.set(driver);
            DRIVER_MAP.put(WEB,WEB_DRIVER_THREAD_LOCAL);
            CONTEXT.set(WEB);
        }
    }

    private static boolean isMobileDriver(WebDriver driver) {
        return driver instanceof AndroidDriver || driver instanceof IOSDriver;
    }

    public static void unload(){
        WEB_DRIVER_THREAD_LOCAL.remove();
        MOBILE_DRIVER_THREAD_LOCAL.remove();
        CONTEXT.remove();
    }
}
