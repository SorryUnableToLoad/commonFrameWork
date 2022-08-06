package com.proj.drivermanager.web.remote.seleniumgrid;

import com.proj.config.ConfigFactory;
import com.proj.enums.BrowserType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public final class SeleniumGridChromeManager {

    private SeleniumGridChromeManager() {
    }

    public static WebDriver getDriver() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setBrowserName(String.valueOf(BrowserType.CHROME));
        return new RemoteWebDriver(ConfigFactory.getConfig().seleniumGridUrl(), desiredCapabilities);

    }
}
