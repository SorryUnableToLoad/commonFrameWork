package com.proj.drivermanager.web.remote.selenoid;

import com.proj.enums.BrowserType;
import org.openqa.selenium.WebDriver;

public final class SelenoidFactory {
    private SelenoidFactory(){

    }
    public static WebDriver getDriver(BrowserType browserType){
        return browserType == BrowserType.CHROME
                ? SelenoidChromeManager.getDriver()
                : SelenoidFirefoxManager.getDriver();
    }
}
