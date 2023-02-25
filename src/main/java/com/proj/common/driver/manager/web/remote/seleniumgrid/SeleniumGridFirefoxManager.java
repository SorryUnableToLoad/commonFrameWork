package com.proj.common.driver.manager.web.remote.seleniumgrid;

import com.proj.common.driver.enums.WebBrowserType;
import com.proj.common.config.factory.FrameworkConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SeleniumGridFirefoxManager {

    private SeleniumGridFirefoxManager() {
    }

    public static WebDriver getDriver() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setBrowserName(String.valueOf(WebBrowserType.FIREFOX));
        return new RemoteWebDriver(FrameworkConfigFactory.getConfig().seleniumGridUrl(), desiredCapabilities);
    }

}
