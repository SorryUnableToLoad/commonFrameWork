package com.proj.common.driver.impl.web;

import com.proj.common.driver.IWebDriver;
import com.proj.common.driver.entity.WebDriverData;
import com.proj.common.driver.factory.web.remote.RemoteWebDriverFactory;
import org.openqa.selenium.WebDriver;

public class RemoteWebDriverImpl implements IWebDriver {
    @Override
    public WebDriver getDriver(WebDriverData driverData) {
        return RemoteWebDriverFactory.getDriver(driverData.getCloudType()
                , driverData.getBrowserType());
    }
}
