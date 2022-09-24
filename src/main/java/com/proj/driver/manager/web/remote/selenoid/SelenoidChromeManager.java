package com.proj.driver.manager.web.remote.selenoid;

import com.proj.config.FrameworkConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public final class SelenoidChromeManager {
    private SelenoidChromeManager() {
    }

    public static WebDriver getDriver() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("browserName", "chrome");
        desiredCapabilities.setCapability("rowserVersion", "101.0");
        desiredCapabilities.setCapability("enableVNC", true);
        desiredCapabilities.setCapability("enableVideo", false);
        return new RemoteWebDriver(FrameworkConfigFactory.getConfig().selenoidUrl(), desiredCapabilities);
    }
}
