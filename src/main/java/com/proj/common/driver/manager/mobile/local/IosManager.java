package com.proj.common.driver.manager.mobile.local;

import com.proj.common.config.factory.FrameworkConfigFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

public final class IosManager {
    private IosManager() {
    }

    public static AppiumDriver getDriver() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.IOS);
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPod touch (7th generation)");
        desiredCapabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "ios-app.zip");
        return new AppiumDriver(FrameworkConfigFactory.getConfig().localAppiumServerURL(), desiredCapabilities)
                ;
    }
}
