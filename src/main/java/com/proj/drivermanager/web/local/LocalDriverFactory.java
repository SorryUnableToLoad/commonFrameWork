package com.proj.drivermanager.web.local;

import com.proj.enums.BrowserType;
import org.openqa.selenium.WebDriver;

import java.util.EnumMap;
import java.util.Map;
import java.util.PrimitiveIterator;
import java.util.function.Supplier;

public final class LocalDriverFactory {
    private LocalDriverFactory() {
    }
    /*
        public static WebDriver getDriver(BrowserType browserType) {
            return (isBrowser(browserType))
                    ? ChromeManager.getDriver()
                    : FirefoxManager.getDriver();
        }

        private static boolean isBrowser(BrowserType browserType) {
            return browserType == BrowserType.CHROME;
        }
    */
    private static final Map<BrowserType, Supplier<WebDriver>> MAP=new EnumMap<>(BrowserType.class);
    /*private static final Supplier<WebDriver> CHROME=ChromeManager::getDriver;
    private static final Supplier<WebDriver> FIREFOX=FirefoxManager::getDriver;
    static {
        MAP.put(BrowserType.CHROME,CHROME);
        MAP.put(BrowserType.FIREFOX,FIREFOX);
    }*/
    static {
        MAP.put(BrowserType.CHROME,ChromeManager::getDriver);
        MAP.put(BrowserType.FIREFOX,FirefoxManager::getDriver);
    }
        public static WebDriver getDriver(BrowserType browserType){
        return MAP.get(browserType).get();
        }
}
