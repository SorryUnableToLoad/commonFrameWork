package com.proj.driver.manager.mobile.remote.browserstack;

import com.proj.config.FrameworkConfigFactory;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserStackIosManager {
    private BrowserStackIosManager(){}
    public static AppiumDriver getDriver() {
        DesiredCapabilities desiredCapabilities=new DesiredCapabilities();
        desiredCapabilities.setCapability("app","bs://c700ce60cf13ae8ed97705a55b8e022f113c5827c");
        desiredCapabilities.setCapability("device","GooglePixel 3");
        desiredCapabilities.setCapability("os_version","12.0");
        desiredCapabilities.setCapability("project","first mobile test project");
        desiredCapabilities.setCapability("build","browser stack build 1");
        desiredCapabilities.setCapability("name","regression test");
        return new AppiumDriver(FrameworkConfigFactory.getConfig().browserStackURL(), desiredCapabilities);
    }
}
