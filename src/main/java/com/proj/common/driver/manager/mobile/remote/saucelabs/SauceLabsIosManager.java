package com.proj.common.driver.manager.mobile.remote.saucelabs;

import com.proj.common.config.factory.FrameworkConfigFactory;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public final class SauceLabsIosManager {

    private SauceLabsIosManager() {
    }

    public static AppiumDriver getDriver() {
        //dummy code and not tested
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("appium:platformVersion", "15.0");
        capabilities.setCapability("appium:deviceName", "iPhone .*");
        capabilities.setCapability("appium:orientation", "portrait");
        capabilities.setCapability("appium:app", "storage:filename=<file-name>");
        DesiredCapabilities sauceOptions = new DesiredCapabilities();
        sauceOptions.setCapability("username", FrameworkConfigFactory.getConfig().sauceLabsUsername());
        sauceOptions.setCapability("accessKey", FrameworkConfigFactory.getConfig().key());
        capabilities.setCapability("sauce:options", sauceOptions);
        return new AppiumDriver(FrameworkConfigFactory.getConfig()
                .sauceLabsUrl(), capabilities);
    }
}
