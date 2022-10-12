package com.proj.driver.manager.mobile.local;

import com.proj.config.FrameworkConfig;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import static com.proj.config.FrameworkConfigFactory.getConfig;

public final class AndroidManager {
    private AndroidManager() {
    }

    private final static String APP_ACTIVITY_NAME = ".MainActivity";
    private final static String APP_PACKAGE_NAME = "in.captainfresh.customer";

    public static AppiumDriver getDriver() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        //desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, APP_ACTIVITY_NAME);
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, getConfig().apkPackageName());
        desiredCapabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/src/test/resources/apkbuilds/app_prod_6_oct_2022.apk");
        //desiredCapabilities.setCapability(MobileCapabilityType.APP,System.getProperty("user.dir")+"/src/test/resources/apkbuilds/android-app.apk");
        return new AppiumDriver(getConfig().localAppiumServerURL(), desiredCapabilities);
    }
}
