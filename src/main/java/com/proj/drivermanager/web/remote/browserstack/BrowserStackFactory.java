package com.proj.drivermanager.web.remote.browserstack;

import com.proj.enums.BrowserType;
import org.openqa.selenium.WebDriver;

public final class BrowserStackFactory {
    private BrowserStackFactory() {
    }

    public static WebDriver getDriver(BrowserType browserType) {
        return browserType == BrowserType.CHROME
                ? BrowserStackChromeManager.getDriver()
                : BrowserStackFirefoxManager.getDriver();
    }
}
