package com.proj.driver.manager.mobile.remote.saucelabs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.proj.config.FrameworkConfigFactory.getConfig;


public final class SauceLabsAndroidManager {

    private SauceLabsAndroidManager(){}

    public static WebDriver getDriver(){
        //dummy code and not tested
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:platformVersion", "11");
        capabilities.setCapability("appium:deviceName", "Samsung.*Galaxy.*");
        capabilities.setCapability("appium:orientation", "portrait");
        capabilities.setCapability("appium:app", "storage:filename=<file-name>");
        DesiredCapabilities sauceOptions = new DesiredCapabilities();
        sauceOptions.setCapability("username", getConfig().saucelabsurl());
        sauceOptions.setCapability("accessKey", getConfig().key());
        capabilities.setCapability("sauce:options", sauceOptions);
        return new RemoteWebDriver(getConfig()
                .saucelabsurl(), capabilities);
    }
}
