package com.proj.driver.factory.web.remote;

import com.proj.driver.manager.web.remote.browserstack.BrowserStackChromeManager;
import com.proj.driver.manager.web.remote.browserstack.BrowserStackFirefoxManager;
import com.proj.driver.enums.WebBrowserType;
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
