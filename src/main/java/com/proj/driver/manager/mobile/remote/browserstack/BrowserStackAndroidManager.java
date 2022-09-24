package com.proj.driver.manager.mobile.remote.browserstack;

import com.proj.config.FrameworkConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public final class BrowserStackAndroidManager {
    private BrowserStackAndroidManager(){}
    public static WebDriver getDriver() {
        DesiredCapabilities desiredCapabilities=new DesiredCapabilities();
        //desiredCapabilities.setCapability("app","bs://c700ce60cf13ae8ed97705a55b8e022f113c5827c");
        desiredCapabilities.setCapability("app","bs://90b692839145cd572797155320003a0036408d23");
        desiredCapabilities.setCapability("device","Samsung Galaxy S21 Ultra");
        desiredCapabilities.setCapability("os_version","11.0");
        desiredCapabilities.setCapability("project","first mobile test project");
        desiredCapabilities.setCapability("build","browser stack build 1");
        desiredCapabilities.setCapability("name","regression test");
        return new RemoteWebDriver(FrameworkConfigFactory.getConfig().browserStackURL(), desiredCapabilities);
    }
}
