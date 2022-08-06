package com.proj.drivermanager.web.local;

import com.proj.enums.BrowserType;
import org.openqa.selenium.WebDriver;

public final class LocalDriverFactory {
    private LocalDriverFactory() {
    }

    public static WebDriver getDriver(BrowserType browserType) {
        return (isBrowser(browserType))
                ? ChromeManager.getDriver()
                 : FirefoxManager.getDriver();
    }

    private static boolean isBrowser(BrowserType browserType) {
        return browserType == BrowserType.CHROME;
    }

}
