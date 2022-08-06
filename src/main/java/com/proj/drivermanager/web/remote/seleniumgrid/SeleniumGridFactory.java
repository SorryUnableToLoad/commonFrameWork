package com.proj.drivermanager.web.remote.seleniumgrid;

import com.proj.enums.BrowserType;
import org.openqa.selenium.WebDriver;

public final class SeleniumGridFactory {
    private SeleniumGridFactory() {
    }

    public static WebDriver getDriver(BrowserType browserType) {
        return browserType == BrowserType.CHROME
                ? SeleniumGridChromeManager.getDriver()
                : SeleniumGridFirefoxManager.getDriver();
    }
}
