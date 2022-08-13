package com.proj.drivermanager.web.remote.seleniumgrid;

import com.proj.enums.WebBrowserType;
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
