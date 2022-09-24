package com.proj.driver.factory.web.remote;

import com.proj.driver.manager.web.remote.seleniumgrid.SeleniumGridChromeManager;
import com.proj.driver.manager.web.remote.seleniumgrid.SeleniumGridFirefoxManager;
import com.proj.driver.enums.WebBrowserType;
import org.openqa.selenium.WebDriver;

public final class SeleniumGridFactory {
    private SeleniumGridFactory() {
    }

    public static WebDriver getDriver(WebBrowserType browserType) {
        return browserType == WebBrowserType.CHROME
                ? SeleniumGridChromeManager.getDriver()
                : SeleniumGridFirefoxManager.getDriver();
    }
}
