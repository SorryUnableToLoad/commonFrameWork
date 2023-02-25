package com.proj.common.driver.factory.web.remote;

import com.proj.common.driver.enums.WebBrowserType;
import com.proj.common.driver.manager.web.remote.browserstack.BrowserStackChromeManager;
import com.proj.common.driver.manager.web.remote.browserstack.BrowserStackFirefoxManager;
import org.openqa.selenium.WebDriver;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public final class BrowserStackFactory {
    /*public static WebDriver getDriver(WebBrowserType webBrowserType) {
        return webBrowserType == WebBrowserType.CHROME
                ? BrowserStackChromeManager.getDriver()
                : BrowserStackFirefoxManager.getDriver();
    }*/
    private static final Map<WebBrowserType, Supplier<WebDriver>> MAP =
            new EnumMap<>(WebBrowserType.class);

    static {
        MAP.put(WebBrowserType.CHROME, BrowserStackChromeManager::getDriver);
        MAP.put(WebBrowserType.FIREFOX, BrowserStackFirefoxManager::getDriver);
    }

    private BrowserStackFactory() {
    }

    public static WebDriver getDriver(WebBrowserType webBrowserType) {
        return MAP.get(webBrowserType).get();
    }
}
