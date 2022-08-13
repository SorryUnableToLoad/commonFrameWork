package com.proj.drivermanager.web.remote.browserstack;

import com.proj.enums.WebBrowserType;
import org.openqa.selenium.WebDriver;

public final class BrowserStackFactory {
    private BrowserStackFactory() {
    }

    public static WebDriver getDriver(WebBrowserType webBrowserType) {
        return webBrowserType == WebBrowserType.CHROME
                ? BrowserStackChromeManager.getDriver()
                : BrowserStackFirefoxManager.getDriver();
    }
}
