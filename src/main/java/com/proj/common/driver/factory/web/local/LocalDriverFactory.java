package com.proj.common.driver.factory.web.local;

import com.proj.common.driver.enums.WebBrowserType;
import com.proj.common.driver.manager.web.local.ChromeManager;
import com.proj.common.driver.manager.web.local.FirefoxManager;
import org.openqa.selenium.WebDriver;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public final class LocalDriverFactory {
    /*
        public static WebDriver getDriver(WebBrowserType browserType) {
            return (isBrowser(browserType))
                    ? ChromeManager.getDriver()
                    : FirefoxManager.getDriver();
        }

        private static boolean isBrowser(BrowserType browserType) {
            return browserType == BrowserType.CHROME;
        }
    */
    private static final Map<WebBrowserType, Supplier<WebDriver>> MAP = new EnumMap<>(WebBrowserType.class);

    /*private static final Supplier<WebDriver> CHROME=ChromeManager::getDriver;
    private static final Supplier<WebDriver> FIREFOX=FirefoxManager::getDriver;
    static {
        MAP.put(WebBrowserType.CHROME,CHROME);
        MAP.put(WebBrowserType.FIREFOX,FIREFOX);
    }*/
    static {
        MAP.put(WebBrowserType.CHROME, ChromeManager::getDriver);
        MAP.put(WebBrowserType.FIREFOX, FirefoxManager::getDriver);
    }

    private LocalDriverFactory() {
    }

    public static WebDriver getDriver(WebBrowserType webbrowserType) {
        return MAP.get(webbrowserType).get();
    }
}
