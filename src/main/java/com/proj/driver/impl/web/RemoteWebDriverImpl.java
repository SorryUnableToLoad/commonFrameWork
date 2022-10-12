package com.proj.driver.impl.web;

import com.proj.driver.entity.WebDriverData;
import com.proj.driver.factory.web.remote.RemoteWebDriverFactory;
import com.proj.driver.IWebDriver;
import org.openqa.selenium.WebDriver;

public class RemoteWebDriverImpl implements IWebDriver {
    @Override
    public WebDriver getDriver(WebDriverData driverData) {
        return RemoteWebDriverFactory.getDriver(driverData.getCloudType()
                ,driverData.getBrowserType());
    }
}
