package com.proj.common.driver.manager.mobile.local;

import com.proj.common.config.factory.FrameworkConfigFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

public final class AndroidManager {
    private final static String APP_ACTIVITY_NAME = ".MainActivity";
    private final static String APP_PACKAGE_NAME = "in.captainfresh.customer";

    private AndroidManager() {
    }

    public static AppiumDriver getDriver() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        //desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, APP_ACTIVITY_NAME);
        //desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, getConfig().apkPackageName());
        //desiredCapabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/src/test/resources/apkbuilds/app_prod_6_oct_2022.apk");
        //desiredCapabilities.setCapability(MobileCapabilityType.APP,System.getProperty("user.dir")+"/src/test/resources/apkbuilds/android-app.apk");
        return new AppiumDriver(FrameworkConfigFactory.getConfig().localAppiumServerURL(), desiredCapabilities);
    }
}
