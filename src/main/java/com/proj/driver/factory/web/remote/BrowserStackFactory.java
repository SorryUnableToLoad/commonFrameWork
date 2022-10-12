package com.proj.driver.factory.web.remote;

import com.proj.config.enums.WebBrowserType;
import com.proj.driver.manager.web.remote.browserstack.BrowserStackChromeManager;
import com.proj.driver.manager.web.remote.browserstack.BrowserStackFirefoxManager;
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
