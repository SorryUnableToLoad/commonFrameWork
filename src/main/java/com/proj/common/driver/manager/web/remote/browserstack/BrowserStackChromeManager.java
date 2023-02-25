package com.proj.common.driver.manager.web.remote.browserstack;

import com.proj.common.config.factory.FrameworkConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public final class BrowserStackChromeManager {
    private BrowserStackChromeManager() {
    }

    public static WebDriver getDriver() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("browserName", "chrome");
        desiredCapabilities.setCapability("browserVersion", "101.0");
        desiredCapabilities.setCapability("os", "Windows");
        desiredCapabilities.setCapability("os_version", "10");
        desiredCapabilities.setCapability("enableVNC", true);
        desiredCapabilities.setCapability("enableVideo", false);
        return new RemoteWebDriver(FrameworkConfigFactory.getConfig().browserStackURL(), desiredCapabilities);
    }
}
