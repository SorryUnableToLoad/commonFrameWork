package com.proj.drivermanager.web.remote.selenoid;

import com.proj.enums.WebBrowserType;
import org.openqa.selenium.WebDriver;

public final class SelenoidFactory {
    private SelenoidFactory(){

    }
    public static WebDriver getDriver(WebBrowserType webBrowserType){
        return webBrowserType == WebBrowserType.CHROME
                ? SelenoidChromeManager.getDriver()
                : SelenoidFirefoxManager.getDriver();
    }
}
