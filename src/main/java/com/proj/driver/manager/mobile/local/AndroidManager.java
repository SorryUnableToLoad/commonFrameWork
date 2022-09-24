package com.proj.driver.manager.mobile.local;

import com.proj.config.FrameworkConfigFactory;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.proj.config.FrameworkConfigFactory.getConfig;

public final class AndroidManager {
    private AndroidManager() {
    }

    public static WebDriver getDriver() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
        desiredCapabilities.setCapability(MobileCapabilityType.APP,  System.getProperty("user.dir")+"/customerapp_build_5_sep_2022.apk");
        return new RemoteWebDriver(FrameworkConfigFactory.getConfig().localAppiumServerURL(), desiredCapabilities);
    }
}
