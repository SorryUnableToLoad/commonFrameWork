package com.proj.driver.factory.web.remote;

import com.proj.driver.manager.web.remote.seleniumgrid.SeleniumGridFirefoxManager;
import com.proj.config.enums.WebBrowserType;
import com.proj.driver.manager.web.remote.seleniumgrid.SeleniumGridChromeManager;
import org.openqa.selenium.WebDriver;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public final class SeleniumGridFactory {
    /*public static WebDriver getDriver(WebBrowserType browserType) {
        return browserType == WebBrowserType.CHROME
                ? SeleniumGridChromeManager.getDriver()
                : SeleniumGridFirefoxManager.getDriver();
    }*/
    private static final Map<WebBrowserType, Supplier<WebDriver>> MAP =
            new EnumMap<>(WebBrowserType.class);

    static {
        MAP.put(WebBrowserType.CHROME, SeleniumGridChromeManager::getDriver);
        MAP.put(WebBrowserType.FIREFOX, SeleniumGridFirefoxManager::getDriver);
    }

    private SeleniumGridFactory() {
    }

    public static WebDriver getDriver(WebBrowserType browserType) {
        return MAP.get(browserType).get();
    }
}
