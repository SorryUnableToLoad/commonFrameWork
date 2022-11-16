package com.proj.driver.factory.web.remote;

import com.proj.config.enums.WebBrowserType;
import com.proj.config.enums.WebCloudType;
import org.openqa.selenium.WebDriver;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

public class RemoteWebDriverFactory {
    private RemoteWebDriverFactory() {
    }
    /*public static WebDriver getDriver(WebCloudType cloudType, BrowserType browserType) {
        if (cloudType == WebCloudType.BROWSERSTACK) {
            return BrowserStackFactory.getDriver(browserType);
        } else if (cloudType == WebCloudType.SELENIUMGRID) {
            return SeleniumGridFactory.getDriver(browserType);
        }else if (cloudType == WebCloudType.SELENOID) {
            return SelenoidFactory.getDriver(browserType);
        }
        return null;
    }*/

    private static final Map<WebCloudType, Function<WebBrowserType, WebDriver>> MAP =
            new EnumMap<>(WebCloudType.class);
    //private static final Function<BrowserType,WebDriver>SELENIUMGRID=browserType -> SelenoidFactory.getDriver();
    private static final Function<WebBrowserType, WebDriver> SELENIUMGRID = SeleniumGridFactory::getDriver;
    private static final Function<WebBrowserType, WebDriver> SELENOID = SelenoidFactory::getDriver;
    private static final Function<WebBrowserType, WebDriver> BROWSERSTACK = BrowserStackFactory::getDriver;

    static {
        MAP.put(WebCloudType.SELENIUMGRID, SELENIUMGRID);
        MAP.put(WebCloudType.SELENOID, SELENOID);
        MAP.put(WebCloudType.BROWSERSTACK, BROWSERSTACK);
    }

    public static WebDriver getDriver(WebCloudType cloudType, WebBrowserType webBrowserType) {
        return MAP.get(cloudType).apply(webBrowserType);
    }
}
