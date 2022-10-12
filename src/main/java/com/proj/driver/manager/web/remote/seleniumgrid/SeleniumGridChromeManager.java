package com.proj.driver.manager.web.remote.seleniumgrid;

import com.proj.config.FrameworkConfigFactory;
import com.proj.config.enums.WebBrowserType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public final class SeleniumGridChromeManager {

    private SeleniumGridChromeManager() {
    }

    public static WebDriver getDriver() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setBrowserName(String.valueOf(WebBrowserType.CHROME));
        return new RemoteWebDriver(FrameworkConfigFactory.getConfig().seleniumGridUrl(), desiredCapabilities);

    }
}
